package backendrestapi.request.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import compas.common.CommonFunctions;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Spring Web filter for logging request and response.
 *
 * @author Hidetake Iwata
 * @see org.springframework.web.filter.AbstractRequestLoggingFilter
 * @see ContentCachingRequestWrapper
 * @see ContentCachingResponseWrapper
 */

public class RequestAndResponseLoggingFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(getClass());
    private final String HTTP_REQUEST_LOG = "HttpRequestLog";
    private final String HTTP_RESPONSE_LOG = "HttpResponseLog";

    
	@Autowired
	private CommonFunctions commonFunctions;

	public RequestAndResponseLoggingFilter(CommonFunctions commonFunctions) {
		super();
		this.commonFunctions = commonFunctions;
	}

	private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(MediaType.valueOf("text/*"),
			MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.valueOf("application/*+json"), MediaType.valueOf("application/*+xml"),
			MediaType.MULTIPART_FORM_DATA);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isAsyncDispatch(request)) {
			//System.out.println("doFilterInternal()>>>isAsyncDispatch...");
			filterChain.doFilter(request, response);
		} else {
			//System.out.println("doFilterInternal()>>>doFilterWrapped...");
			doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
		}
	}

	protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			//System.out.println("doFilterWrapped()>>>beforeRequest...");
			beforeRequest(request, response);
			filterChain.doFilter(request, response);
		} finally {
			//System.out.println("doFilterWrapped()>>>afterRequest...");
			afterRequest(request, response);
			response.copyBodyToResponse();
		}
	}

	protected void beforeRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {

		logRequestHeader(request, request.getRemoteAddr() + "|>");

	}

	protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {

		logRequestBody(request, request.getRemoteAddr() + "|>");
		logResponse(response, request.getRemoteAddr() + "|<");

	}

	private static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
		String queryString = request.getQueryString();
		if (queryString == null) {
			// log.info("{} {} {}", prefix, request.getMethod(),
			// request.getRequestURI());
		} else {
			// log.info("{} {} {}?{}", prefix, request.getMethod(),
			// request.getRequestURI(), queryString);
		}
		// Collections.list(request.getHeaderNames()).forEach(headerName ->
		// Collections.list(request.getHeaders(headerName)).forEach(headerValue
		// ->
		// log.info("{} {}: {}", prefix, headerName, headerValue)));
		// log.info("{}", prefix);
	}

	private void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
		byte[] content = request.getContentAsByteArray();
		if (content.length > 0) {
			logContent(HTTP_REQUEST_LOG, content, request.getContentType(), request.getCharacterEncoding(), prefix);
		}
	}

	private void logResponse(ContentCachingResponseWrapper response, String prefix) {
		int status = response.getStatus();
		// log.info("{} {} {}", prefix, status,
		// HttpStatus.valueOf(status).getReasonPhrase());
		// response.getHeaderNames().forEach(headerName ->
		// response.getHeaders(headerName).forEach(headerValue ->
		// log.info("{} {}: {}", prefix, headerName, headerValue)));
		// log.info("{}", prefix);
		//
		byte[] content = response.getContentAsByteArray();
		if (content.length > 0) {
			logContent(HTTP_RESPONSE_LOG, content, response.getContentType(), response.getCharacterEncoding(), prefix);
		}
	}

	private void logContent(String type, byte[] content, String contentType, String contentEncoding, String prefix) {

		if (HTTP_REQUEST_LOG.equals(type)) {
			//
		} else if (HTTP_RESPONSE_LOG.equals(type)) {
			MediaType mediaType = MediaType.valueOf(contentType);
			boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
			
			//logger.info(String.format("mediaType: %s  visible: %s ", mediaType, visible));
			
			//System.out.println("mediaType: "+mediaType+", visible: "+visible);

			if (visible) {
				try {
					String posApiResponse = new String(content, contentEncoding);

					commonFunctions.updatePosrequestslog(posApiResponse);

//					Stream.of(posApiResponse.split("\r\n|\r|\n"))
//							.forEach(line -> System.out.println(String.format("%s [%s ", prefix, line)));

				} catch (UnsupportedEncodingException e) {
					//System.out.println(String.format("%s [%d  bytes content]", prefix, content.length));
				}
			} else {
				//System.out.println(String.format("%s [%d bytes content]", prefix, content.length));
			}
		}
	}

	private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper) {
			return (ContentCachingRequestWrapper) request;
		} else {
			return new ContentCachingRequestWrapper(request);
		}
	}

	private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper) {
			return (ContentCachingResponseWrapper) response;
		} else {
			return new ContentCachingResponseWrapper(response);
		}
	}
}