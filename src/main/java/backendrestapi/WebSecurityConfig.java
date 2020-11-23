package backendrestapi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import backendrestapi.jwt.JWTAuthenticationFilter;
import backendrestapi.jwt.JWTAuthorizationFilter;
import backendrestapi.jwt.SecurityConstants;
import backendrestapi.jwt.UserDetailsServiceImpl;
import backendrestapi.request.filter.CsrfTokenFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
//		web.ignoring().antMatchers("/api/services/controller/user/login");
	}
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.addFilterAfter( new CsrfTokenFilter(), CsrfFilter.class)
	 * .authorizeRequests() .anyRequest().permitAll(); }
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		RequestMatcher csrfRequestMatcher = new RequestMatcher() {
            private RegexRequestMatcher requestMatcher =
//                    new RegexRequestMatcher("/urls-with-csrf-check/**", null);
            new RegexRequestMatcher("/api/services/controller/user/login", null);

            public boolean matches(HttpServletRequest httpServletRequest) {
                if (requestMatcher.matches(httpServletRequest)) {
                    return false;
                }
                return true;
            }
        };
		/*
		 * 
		 * http.cors().and().csrf().disable() .authorizeRequests() .and()
		 * .exceptionHandling() //.accessDeniedHandler(accessDeniedHandler)
		 * .authenticationEntryPoint(restAuthenticationEntryPoint) .and()
		 * .authorizeRequests() .antMatchers(HttpMethod.POST,
		 * SecurityConstants.SIGN_UP_URL).permitAll()
		 * .anyRequest().authenticated() .and() .addFilter(new
		 * JWTAuthenticationFilter(authenticationManager())) .addFilter(new
		 * JWTAuthorizationFilter(authenticationManager()))
		 * 
		 * // this disables session creation on Spring Security
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
		 * STATELESS);
		 */

		/*
		 * http.csrf().disable() .authorizeRequests() .and()
		 * .exceptionHandling() .accessDeniedHandler(accessDeniedHandler)
		 * .authenticationEntryPoint(restAuthenticationEntryPoint) .and()
		 * .authorizeRequests() .antMatchers("/api/csrfAttacker*").permitAll()
		 * .antMatchers("/api/customer/**").permitAll()
		 * .antMatchers("/api/foos/**").authenticated()
		 * .antMatchers("/api/async/**").permitAll()
		 * .antMatchers("/api/admin/**").hasRole("ADMIN") .and() .formLogin()
		 * .successHandler(mySuccessHandler) .failureHandler(myFailureHandler)
		 * .and() .httpBasic() .and() .logout();
		 */

		// http.cors().and().csrf().disable().authorizeRequests()
		http
		.csrf()
        .requireCsrfProtectionMatcher(csrfRequestMatcher)
        .and().addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class).authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll().anyRequest().authenticated()
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager(), userDetailsService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		// auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}