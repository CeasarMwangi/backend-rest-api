package backendrestapi.common;

import org.slf4j.LoggerFactory;

public class AppLogger {

	public static final ch.qos.logback.classic.Logger appRequestsLogger = 
			  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("appRequestsLogger");
	
}
