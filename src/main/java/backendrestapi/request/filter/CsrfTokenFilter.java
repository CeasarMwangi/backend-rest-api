package backendrestapi.request.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;

import com.google.gson.Gson;

public class CsrfTokenFilter implements Filter {
	 
	  private Logger logger =
	          Logger.getLogger(CsrfTokenFilter.class.getName());
	  private Gson gson = new Gson();
	  @Override
	  public void doFilter(
	    ServletRequest request,
	    ServletResponse response,
	    FilterChain filterChain)
	      throws IOException, ServletException {
	 
	      Object o = request.getAttribute("_csrf");
	      CsrfToken token = (CsrfToken) o;
	      Cookie cookie = new Cookie("XSRF-TOKEN", token.getToken());
	      
	 
	      logger.info("CSRF token " + token.getToken());
	 // {"token":"e2a77e18-1078-4550-b5f2-469bc5de79c5","parameterName":"_csrf","headerName":"X-CSRF-TOKEN"}
      
	      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	        httpServletResponse.setHeader("_csrf_obj", gson.toJson(token));
	        httpServletResponse.setHeader("_csrf", token.getToken());
	        
	        //add cookie to response
	        httpServletResponse.addCookie(cookie);
	      filterChain.doFilter(request, response);
	  }
	}
