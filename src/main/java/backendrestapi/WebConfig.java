package backendrestapi;
//package compas;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
///**
// * Created by CLLSDJACKT013 on 9/11/2018.
// */
//
//@Component
//public class WebConfig extends OncePerRequestFilter  {
//    @Override
//    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
//                                    final FilterChain filterChain) throws ServletException, IOException {
//        //response.addHeader("Access-Control-Allow-Origin", "*");//"http://localhost:8595");
////        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
//        response.addHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
//        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
//        response.addHeader("Access-Control-Allow-Credentials", "true");;
//        response.addIntHeader("Access-Control-Max-Age", 10);
//        response.setHeader("X-Frame-Options", "DENY");
//        response.setHeader("X-Content-Type-Options", "nosniff");
//        response.setHeader("X-XSS-Protection", "1; mode=block");
//        response.addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate, no-transform");
//        response.addHeader("Pragma", "no-cache");
//        //X-content-type-options
//        /*
//         response.setHeader("Strict-Transport-Security","max-age=31536000 ; includeSubDomains");
//        response.setHeader("X-Content-Type-Options", "nosniff");
//        response.setHeader("X-Frame-Options", "DENY");
//        response.setHeader("X-XSS-Protection", "1; mode=block");
//        response.setHeader("Content-Security-Policy", "default-src 'self'");
//         */
//        filterChain.doFilter(request, response);
//    }
//}
