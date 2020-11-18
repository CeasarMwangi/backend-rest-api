//package backendrestapi;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import com.google.common.collect.ImmutableList;
//
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	/*
//    	http.cors()
//        .and()
//          .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/user/info", "/api/foos/**")
//              .hasAuthority("SCOPE_read")
//            .antMatchers(HttpMethod.POST, "/api/foos")
//              .hasAuthority("SCOPE_write")
//            .anyRequest()
//              .authenticated();
//    	*/
//    	/*
//    	http.csrf().disable().cors().and().authorizeRequests()
//    	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .antMatchers(HttpMethod.POST, "/api/**").permitAll()
//        .anyRequest().authenticated();
//        //.and().addFilterBefore(new DemoAuthenticationFilter(), BasicAuthenticationFilter.class);
////http.csrf().disable();
//    	*/
//    	
//        http
//        .httpBasic().disable()
//        .cors().and().csrf().disable()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//            .authorizeRequests()
//            
//            .antMatchers("/auth/signin").permitAll()
////            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////            .antMatchers(HttpMethod.POST, "/api/**").authenticated()
//            .antMatchers(HttpMethod.POST, "/api/**").permitAll()
////            .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
//            .anyRequest().permitAll();//.authenticated();
////        .and().apply(new JwtSecurityConfigurer(jwtTokenProvider));
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(ImmutableList.of("http://localhost:8090"));
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        
//        configuration.setAllowedMethods(Arrays.asList("POST","OPTIONS"));
//        configuration.setAllowCredentials(true);
//        
////        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source; 
//    }
//}
