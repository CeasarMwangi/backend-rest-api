package backendrestapi.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    final HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcast-instance");


    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        System.out.println(" doFilterInternal....");
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            System.out.println("doFilterInternal invalid Authorization header....");
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        System.out.println(" getAuthentication....");
        try {
            String token = request.getHeader(SecurityConstants.HEADER_STRING);
            System.out.println(" REQUEST token:=> " + token);
            if (token != null) {
                // parse the token.
                //System.out.println(AppUtilities.logPreString() + " Passing the token....");
                String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                        .build()
                        .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getSubject();

                //System.out.println(AppUtilities.logPreString() + " Get the token from Hazelcast in-memory database");
                //Different types of maps are available like IMap, ConcrrentMap, MultiMap etc.
                //IMap is Concurrent, distributed, observable and queryable map.
                // IMap does not allow nulls to be used as keys or values.
                IMap<String, String> hazelcastMap = hazelcastInstance.getMap("logged-in-username-tokens-map");
                String cachedUserToken = hazelcastMap.get(user);

                System.out.println(" user: " + user + ", cachedUserToken: " + cachedUserToken);
                


                if (token.equals(cachedUserToken) && user != null) {
                    System.out.println(" Token is valid....");
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }

                System.out.println(" Invalid token from in-memory database....");
                return null;
            }
        } catch (Exception ex) {
            
            System.out.println("Exception::: " + ex.getMessage());


        }
        return null;
    }
}