package backendrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import backendrestapi.request.interceptor.AppRequestsInterceptor;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer
{
    @Autowired
    AppRequestsInterceptor appRequestsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(appRequestsInterceptor);
    }
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
//                .allowedMethods("GET");
//        // registry.addMapping("/api/**").allowedOrigins("https://developer.mozilla.org/");//("http://localhost:8595");
//
//    }
}
