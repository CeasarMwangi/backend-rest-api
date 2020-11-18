package backendrestapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backendrestapi.common.AppUtilities;
import backendrestapi.logger.ApiLogger;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebUtils {

	@Autowired
    private HttpServletRequest request;
	@Autowired
	private ApiLogger apiLogger;

    public String getClientIp() {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
    
    public boolean isClientAuthenticated(){
    	boolean response = false;
    	try{
    		if (request != null) {
                String security_key = request.getHeader("security_key");
                String signature = request.getHeader("signature");
                String api_username = request.getHeader("api_username");
                String api_password = request.getHeader("api_password");
                String api_key = request.getHeader("api_key");
                
                String transId = request.getHeader("transId");
                String action = request.getHeader("action");
                
                
                
                
                
                
                
                
                
                
                response = true;
            }
    		
    	} catch (Exception ex) {

			String errorMessage = AppUtilities.logPreString() + AppUtilities.ERROR + ex.getMessage()
					+ AppUtilities.STACKTRACE + AppUtilities.getExceptionStacktrace(ex);
			apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);
		}
    	return response;
    }

}