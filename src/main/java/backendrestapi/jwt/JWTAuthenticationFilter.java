package backendrestapi.jwt;


import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import backendrestapi.models.Role;
import backendrestapi.models.Users;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private UserDetailsServiceImpl userDetailsService;
    final HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcast-instance");

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        setFilterProcessesUrl("/api/services/controller/user/login"); 
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            //System.out.println("Login attemptAuthentication....");
            Users creds = (Users) new ObjectMapper()
                    .readValue(req.getInputStream(), Users.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            getUserAuthorities(creds.getUsername()))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String username = ((User) auth.getPrincipal()).getUsername();
        System.out.println("Login username: "+username);
        System.out.println("Login Authorities: "+((User) auth.getPrincipal()).getAuthorities());
        
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));

        System.out.println("Login token: "+token);
       // System.out.println("Save token in Hazelcast in-memory database");
        //
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("logged-in-username-tokens-map");
        // write value, This value will be accessible from another jvm also
        hazelcastMap.put(username, SecurityConstants.TOKEN_PREFIX + token);


        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(
    		  Collection<Role> roles) {
    		    List<GrantedAuthority> authorities
    		      = new ArrayList<>();
    		    for (Role role: roles) {
    		        authorities.add(new SimpleGrantedAuthority(role.getName()));
    		        role.getPrivileges().stream()
    		         .map(p -> new SimpleGrantedAuthority(p.getName()))
    		         .forEach(authorities::add);
    		    }
    		    
    		    return authorities;
    		}
    
    private Collection<? extends GrantedAuthority> getUserAuthorities(String username){

    	try{
    		UserDetails uDetails = userDetailsService.loadUserByUsername(username);
    		if(uDetails != null){
    			System.out.println("SIZE::: "+uDetails.getAuthorities().size());
    			return uDetails.getAuthorities();
    		}
    		
    	}catch(Exception ex){
    		
    	}
    	System.out.println("SIZE::: "+0);
    	return new ArrayList<>();
    }
}
