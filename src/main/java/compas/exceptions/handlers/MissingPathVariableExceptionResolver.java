package compas.exceptions.handlers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * https://skryvets.com/blog/2018/12/27/enhance-exception-handling-when-building-restful-api-with-spring-boot#remove-whitelabel-error-page
 * 
 * # ##########################################
# Disable the Whitelabel Error Page
server.error.whitelabel.enabled=false

# First one sets Spring to throw NoHandlerFoundException exception, 
# which we can easily catch later on to generate a proper response. 
# Second removes default mapping to static pages to allow us to return 
# JSON (by default, Spring tries to find HTML page with the same name 
# as a String returned from the Controller's method).

spring.mvc.throw-exception-if-no-handler-found=true
spring.resources.add-mappings=false
# ##########################################


 * @author user
 *
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MissingPathVariableExceptionResolver {

    @ExceptionHandler(MissingPathVariableException.class)
    public HashMap<String, String> handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Required path variable is missing in this request. Please add it to your request.");
        return response;
    }
}
