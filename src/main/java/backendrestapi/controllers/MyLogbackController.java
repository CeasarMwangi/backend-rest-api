package backendrestapi.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendrestapi.common.AppLogger;
import backendrestapi.common.AppUtilities;


@RestController
@RequestMapping(path = AppUtilities.BASE_API_PATH + "/logback/logging")

public class MyLogbackController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping(value = "/log-to-file")
    public String index() {
    	String logMsg = "In this article, we saw how to use logging in Spring Boot and how to customize it "
    			+ "further to suit our requirements. But to fully leverage the benefits, the logging capabilities "
    			+ "of the framework need to be complemented with robust and standardized logging practices"
    			+ " in engineering teams. These practices will also need to be enforced with a mix of peer "
    			+ "reviews and automated code quality tools. Everything taken together will ensure that "
    			+ "when production errors happen we have the maximum information available for our diagnosis. "
    			+ "You can refer to all the source code used in the article on Github.";
    	
    	for(int i = 0; i <10000; i++){
    		AppLogger.appRequestsLogger.trace(logMsg);
    		AppLogger.appRequestsLogger.debug(logMsg);
    		AppLogger.appRequestsLogger.info(logMsg);
    		AppLogger.appRequestsLogger.warn(logMsg);
    		AppLogger.appRequestsLogger.error(logMsg);
    	}
 
        return "123456 => "+bCryptPasswordEncoder.encode("123456");
    }
}
