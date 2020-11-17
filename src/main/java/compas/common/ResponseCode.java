package compas.common;

/**
 * Created by Ceasar on 06/05/2019.
 */
public class ResponseCode {
/*
Code Meaning 
000 Success 
303 General error 
155 Unknown credentials supplied/ user credentials blocked 
404 Unknown request action 
114 Insufficient funds 
911 Transaction timed out 
154 Transaction not found/Teller acct not found 
156 Missing required fields 
157 Transaction failed 
149 Duplicate transaction ID 
139 Student not found 
148 Part payment not allowed
*/
	
	//	//116 is insufficient funds and 114 is invalid account

	public static final String RC_000_SUCCESS = "000";
	public static final String RC_116_INSUFFICIENT_FUNDS = "116";
	public static final String RC_114_INVALID_ACCOUNT = "114";
	public static final String RC_999_STR_DEFAULT_RES_CODE = "999";
	public static final String RC_400_STR_BAD_REQUEST = "400";
	public static final String RC_500_STR_INTERNAL_SERVER_ERROR = "500";
	public static final String RC_401_UNAUTHORISED_ACCESS = "401";
	public static final String RC_403_FORBIDDEN = "403";
	
	public static final String RC_911_TRANSACTION_TIME_OUT = "911";

	
	/*

	apiResponse.setResponse_code(ResponseCode.RC_400_STR_BAD_REQUEST);
	
	apiResponse.setResponse_code(ResponseCode.RC_500_STR_INTERNAL_SERVER_ERROR);
	
	apiResponse.setResponse_code(ResponseCode.RC_401_UNAUTHORISED_ACCESS);
	
	apiResponse.setResponse_code(ResponseCode.RC_000_SUCCESS);
	
	ResponseCode.RC_000_SUCCESS.equals(response_code)
	
	
	
	HttpStatus.BAD_REQUEST
	HttpStatus.CREATED - 201
	400 Bad Request.
	public static final HttpStatus UNAUTHORIZED
401 Unauthorized.
public static final HttpStatus INTERNAL_SERVER_ERROR
500 Internal Server Error.

	 */

}
