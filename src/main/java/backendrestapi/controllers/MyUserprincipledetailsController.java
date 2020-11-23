package backendrestapi.controllers;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import backendrestapi.authentication.IAuthenticationFacade;

@RestController
@RequestMapping(path = "/loggedin/user")
public class MyUserprincipledetailsController {
	 @Autowired
	    private IAuthenticationFacade authenticationFacade;
	 
	    @RequestMapping(value = "/get-details-1", method = RequestMethod.POST)
	    @ResponseBody
	    public String currentUserNameSimple() {
	        Authentication authentication = authenticationFacade.getAuthentication();
	        return "Username: "+authentication.getName();
	    }
	    

	/**
	 * Sending X-CSRF-TOKEN as a Request Header
	 * 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/get-details-2")
	public String process_X_CSRF_TOKEN(HttpServletRequest request) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			
//			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//			System.out.println("User has authorities: " + userDetails.getAuthorities());
			// getUsername() - Returns the username used to authenticate the user.
//			System.out.println("User name: " + userDetails.getUsername());

			// getAuthorities() - Returns the authorities granted to the user.
//			System.out.println("User has authorities: " + userDetails.getAuthorities());

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				String username = ((UserDetails)principal).getUsername();
				System.out.println("username: " +username); 
			} else {
			  String username = principal.toString();
			  System.out.println("username: " +username); 
			}
			
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}

		return "POST";
	}
	
	@RequestMapping(value = "/get-details-3", method = RequestMethod.POST)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
	
	
	@RequestMapping(value = "/get-details-4", method = RequestMethod.POST)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
		Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();

		for (SimpleGrantedAuthority permission : list) {
		     System.out.println(permission.getAuthority());
		}
        return authentication.getName();
    }
	
	
	@RequestMapping(value = "/get-details-5", method = RequestMethod.POST)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
	
	
	

}
