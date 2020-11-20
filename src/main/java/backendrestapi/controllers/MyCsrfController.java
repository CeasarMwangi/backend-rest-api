package backendrestapi.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/csrf")
public class MyCsrfController {

	/**
	 * Sending X-CSRF-TOKEN as a Request Header
	 * 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/send-X_CSRF_TOKEN-as-request-header")
	public String process_X_CSRF_TOKEN(HttpServletRequest request) throws Exception {

		final String X_CSRF_TOKEN = request.getHeader("X-CSRF-TOKEN");

		System.out.println("X_CSRF_TOKEN: " + X_CSRF_TOKEN);

		return "POST";
	}

	/**
	 * Sending _csrf as a Request Body->form-data
	 * 
	 * 
	 * @param _csrf
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/send-csrf-as-request-body-form-data")
	public String process(HttpServletRequest request, @RequestPart("_csrf") String _csrf) throws Exception {
		System.out.println("_csrf: " + _csrf);

		final String headerCsrf = request.getHeader("_csrf");
		System.out.println("headerCsrf: " + headerCsrf);

		return "POST";
	}

}
