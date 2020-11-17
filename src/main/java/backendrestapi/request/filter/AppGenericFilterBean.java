package backendrestapi.request.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppGenericFilterBean extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        
	        /*
	        if (req.getHeader("x-dawson-nonce") == null || req.getHeader("x-dawson-signature") == null) {
	            httpResponse.setContentType("application/json");
	            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required headers not specified in the request");
	            System.out.println("Required headers not .....");
	            return;
	        }
	        
	        chain.doFilter(request, response);
	        */
	        
	        
	                 ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(req);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

//        Instant start = Instant.now();
        System.out.println("START .....");


        chain.doFilter(requestWrapper, responseWrapper);
        System.out.println("FINISH .....");

//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).toMillis();

        byte[] responseArray = responseWrapper.getContentAsByteArray();
        String responseStr = new String(responseArray, responseWrapper.getCharacterEncoding());
        
        //updatePosrequestslog(String posApiResponse)
	         
	         
	        
	   
		
	}


}