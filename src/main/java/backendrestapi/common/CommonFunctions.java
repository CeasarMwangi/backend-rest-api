package backendrestapi.common;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.MimeMultipart;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import backendrestapi.logger.ApiLogger;
import backendrestapi.models.apiresponse.ApiResponse;



/**
 * Created by Ceasar on 07/05/2019.
 */

@Component
public class CommonFunctions {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private Gson gson = new Gson();

	private static final String api_wrapper_security_key = "j-gDbsC_Gg24wQ";
	private static final String api_wrapper_username = "PbuAgencyAPIWrapper#";
	private static final String api_wrapper_password = "aLATgxk_0v7E0s4g";
	private static final String api_wrapper_api_key = "wOW0G8TwGfNLckQ";

	@Autowired
	private ApiLogger apiLogger;

	
	@Value("${api.logpath}")
	private String logpath;

	public String traverseJsonObjectObject(HttpServletRequest httpServletRequest, String jsonString,
			boolean checkDuplicates) {
		JsonObject obj = new JsonObject();
		try {
			final JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

			boolean isClientValid = true;
			if (isClientValid) {

				for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
					String key = entry.getKey();
					String value = jsonObject.get(entry.getKey()).getAsString();

					if (!"cardPinBlock".equals(key) && !"cardTrack2Data".equals(key) && !"cardPAN".equals(key)
							&& !"cardICCData".equals(key) && !"cardTrack1Data".equals(key) && !"Extras".equals(key)
							&& !"customer_photo".equals(key) && !"customer_id".equals(key)
							&& !"customer_form_photo".equals(key) && !"fingerPrint_base64string".equals(key)) {
						// Tue, 28 May 2019,09:14:46
						// "menu": "select_area_of_residence",
						// "From": "23-May-2019"
						// "transaction_date": "2019\/06\/20 13:03:55",
						// value = value.replaceAll("[^0-9.a-zA-Z\\s/]",
						// "").trim();
						// value = value.replaceAll("[^-a-zA-Z0-9.\\s/:,\\\\_]",
						// "").trim();
						value = value.replaceAll("[^-a-zA-Z0-9.\\s/:,_@]", "").trim();
					}
					if (key.equals("depositedBy")) {
						obj.addProperty("narration", value);
					}

					if (key.equals("name")) {
						obj.addProperty("customer_name", value);
					}

					obj.addProperty(key, value);

				}

				obj.addProperty("INSTALLED_API_VERSION", AppUtilities.INSTALLED_API_VERSION);
				obj.addProperty("INSTALLED_API_VERSION_BUILD_DATE", AppUtilities.INSTALLED_API_VERSION_BUILD_DATE);
				obj.addProperty("IS_APP_IN_DEBUG_MODE", AppUtilities.IS_APP_IN_DEBUG_MODE);

				String request_type = obj.get(RequestFields.REQUEST_FIELD_REQUEST_TYPE).getAsString();
				if (request_type.equals(ESBRequestType.PAY_UMEME_USING_CASH)
						|| request_type.equals(ESBRequestType.PAY_URA_USING_CASH)
						|| request_type.equals(ESBRequestType.BILLPAY_NWSC_USING_CASH)) {
					// This applies for UMEME,URA and NWSC too

					obj.addProperty(RequestFields.REQUEST_FIELD_PBU_MIDDLEWARE_TransrefNo,
							obj.get(AppUtilities.UTILITY_CUSTOMER_VALIDATION_STEP_RESPONSE_REQUEST_ID).getAsString());

				}



				//
				String timeStamp = AppUtilities.genericDatetime("yyyy-MM-dd HH:mm:ss");
				obj.addProperty(RequestFields.REQUEST_FIELD_receivedAtTimestamp, timeStamp);

				///////////////////////
				String utility = obj.has("utility") ? obj.get("utility").getAsString() : "";

				String CustRef = obj.has("CustRef") ? obj.get("CustRef").getAsString() : "";
				String PhoneNo = obj.has("PhoneNo") ? obj.get("PhoneNo").getAsString() : "";

				if (CustRef.trim().length() == 0 && PhoneNo.trim().length() > 0) {
					CustRef = PhoneNo;
				}
				String transactionNarration = "";
				if (request_type.equals(ESBRequestType.BANK_2_WALLET_TRANSFERS_USING_BIO)
						|| request_type.equals(ESBRequestType.BANK_2_WALLET_TRANSFERS_USING_OTP)) {
					transactionNarration = "B2W" + "-" + obj.get("beneficiaryPhoneNo").getAsString();

				} else {
					transactionNarration = utility + "-" + CustRef;
				}

				// customer_contact

				if (request_type.equals(ESBRequestType.LOAN_REQUEST_USING_OTP)
						|| request_type.equals(ESBRequestType.LOAN_REQUEST_USING_BIO)
						|| request_type.equals(ESBRequestType.LOAN_REQUEST_USING_ATMCARD)
						|| request_type.equals(ESBRequestType.ATM_CARD_REQUEST_USING_BIO)
						|| request_type.equals(ESBRequestType.ATM_CARD_REQUEST_USING_OTP)) {

					if (!obj.has("phone")) {
						String customer_contact = obj.has("customer_contact")
								? obj.get("customer_contact").getAsString() : "";
						obj.addProperty("phone", customer_contact);
					}

				}

				obj.addProperty("transactionNarration", transactionNarration);

				////////////
				// Log - PosrequestslogRepository
				////////////
				logPosrequests(obj);
			} else {
				obj = new JsonObject();

				String timeStamp = AppUtilities.genericDatetime("yyyy-MM-dd HH:mm:ss");

				ApiResponse apiResponse = new ApiResponse();

				apiResponse.setResponse_code(ResponseCode.RC_403_FORBIDDEN);
				apiResponse.setResponse_status(false);
				apiResponse.setResponse_message("FORBIDDEN");
				apiResponse.setTrn_receipt_number("BADREQUEST" + timeStamp.replaceAll("[^0-9]", ""));

				obj = gson.fromJson(gson.toJson(apiResponse), JsonObject.class);
				obj.addProperty(AppUtilities.UNTRUSTED_CLIENT, "UNTRUSTED CLIENT");

			}
		} catch (Exception ex) {
			String errorMessage = AppUtilities.logPreString() + "POS REQUEST: " + jsonString + AppUtilities.ERROR
					+ ex.getMessage() + AppUtilities.STACKTRACE + AppUtilities.getExceptionStacktrace(ex);
			apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);

			obj = new JsonObject();

		}

		logTraverseRequestOBJ(obj);
		return gson.toJson(obj);
	}

	private void logTraverseRequestOBJ(JsonObject obj) {
		try {
			if (AppUtilities.IS_APP_IN_DEBUG_MODE) {
				apiLogger.doLogCheckSensitiveData(AppUtilities.LOG_FILE_DEBUG_FILE, AppUtilities.logPreString(), obj);
			}
		} catch (Exception ex) {
			String errorMessage = AppUtilities.logPreString() + "POS REQUEST OBJ: " + gson.toJson(obj)
					+ AppUtilities.ERROR + ex.getMessage() + AppUtilities.STACKTRACE
					+ AppUtilities.getExceptionStacktrace(ex);
			apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);

		}
	}

	public void logPosrequests(JsonObject obj) {
		try {
			String receipt_number = obj.get(AppUtilities.RECEIPT_NUMBER).getAsString();
			String receivedAtTimestamp = obj.get(RequestFields.REQUEST_FIELD_receivedAtTimestamp).getAsString();

			String menu = obj.has("menu") ? obj.get("menu").getAsString() : "";
			//

			// REQUEST_FETCH_USERS
			String request_type = obj.has("requestType") ? obj.get("requestType").getAsString() : "N/A";

			String narration = obj.has("narration") ? obj.get("narration").getAsString() : "";
			if ("".equals(narration)) {
				narration = request_type.replace("_", " ");
			}

			obj.addProperty("narration", narration);

			apiLogger.doLog(AppUtilities.LOG_FILE_DEBUG_FILE, " request_type: " + request_type);

			if (!ESBRequestType.REQUEST_LOGIN.equals(request_type)
					&& !ESBRequestType.REQUEST_FETCH_USERS.equals(request_type)
					&& !ESBRequestType.BILL_PRESENTMENT_NWSC_GET_AREAS.equals(request_type)
					&& !ESBRequestType.BILL_PRESENTMENT_URA_GET_BRANCHES.equals(request_type)
					&& !ESBRequestType.BILL_PRESENTMENT_GET_GOTV_BOUQUET_LIST.equals(request_type)
					&& !ESBRequestType.BILL_PRESENTMENT_GET_DSTV_BOUQUET_LIST.equals(request_type)
					&& !menu.equals("PAYBILL_AZAMTV_PACKAGES")) {
				


				
			}

		} catch (Exception ex) {
			String errorMessage = AppUtilities.logPreString() + "POS REQUEST OBJ: " + gson.toJson(obj)
					+ AppUtilities.ERROR + ex.getMessage() + AppUtilities.STACKTRACE
					+ AppUtilities.getExceptionStacktrace(ex);
			apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);

		}
	}

	public void updatePosrequestslog(String posApiResponse) {
		JsonObject originalRequest = new JsonObject();
		try {
			if (!posApiResponse.contains(ESBRequestType.REQUEST_FETCH_USERS)
					&& !posApiResponse.contains("PAYBILL_NWSC_AREAS") && !posApiResponse.contains("Data\":[")
					&& !posApiResponse.contains("Data\":[") && !posApiResponse.contains("PAYBILL_AZAMTV_PACKAGES")) {

				ApiResponse apiResponse = gson.fromJson(posApiResponse, ApiResponse.class);

				if (apiResponse != null) {
					boolean response_status = apiResponse.getResponse_status() != null
							? apiResponse.getResponse_status() : false;
					String response_code = apiResponse.getResponse_code() != null ? apiResponse.getResponse_code()
							: ResponseCode.RC_999_STR_DEFAULT_RES_CODE;
					String response_message = apiResponse.getResponse_message() != null
							? apiResponse.getResponse_message() : "";


						String receipt_number = "";

						Thread t = new Thread(new Runnable() {
							public void run() {

							};
						});
						t.start();
					
				}
			}

		} catch (Exception ex) {
			String errorMessage = AppUtilities.logPreString() + "POS REQUEST originalRequest: "
					+ gson.toJson(originalRequest) + "]]][[[ POS RESPONSE posApiResponse: " + posApiResponse
					+ AppUtilities.ERROR + ex.getMessage() + AppUtilities.STACKTRACE
					+ AppUtilities.getExceptionStacktrace(ex);
			apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);

		}
	}

	public void updatePosrequestslog(JsonObject originalRequest, String receipt_number, ApiResponse apiResponse) {
		try {
			if (apiResponse != null) {
				String response_code = apiResponse.getResponse_code() != null ? apiResponse.getResponse_code()
						: ResponseCode.RC_999_STR_DEFAULT_RES_CODE;
				String response_message = apiResponse.getResponse_message() != null ? apiResponse.getResponse_message()
						: "";

				Thread t = new Thread(new Runnable() {
					public void run() {


					};
				});
				t.start();
			}

		} catch (Exception ex) {
			String errorMessage = AppUtilities.logPreString() + "POS REQUEST originalRequest: "
					+ gson.toJson(originalRequest) + AppUtilities.ERROR + ex.getMessage() + AppUtilities.STACKTRACE
					+ AppUtilities.getExceptionStacktrace(ex);
			apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);

		}
	}

	
	public Double generateLastThreeRandomNumbers(Integer max, Integer min) {
		return Math.floor(Math.random() * (max - min) + min);
	}

	public String generateSwitchReqPingRefNo() {
		// return LocalDateTime.now().toString().replace(":", "").replace(".",
		// "").replace("-", "");
		return AppUtilities.genericDatetime("yyyy-MM-dd HH:mm:ss.SSS").replaceAll("([^0-9])", "");

	}


	public static String currencyFormat(String number) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(new BigDecimal(number));
	}


	/*
	 * https://www.javacodeexamples.com/check-password-strength-in-java-example/
	 * 668
	 * 
	 * We modified our method to check for the number of occurrences too using a
	 * regular expression. For example, “(?=.*[0-9].*[0-9]).*” expression checks
	 * if the string contains at least 2 digits where,
	 * 
	 * ?= - positive look ahead for the following condition .* - any character
	 * [0-9] - followed by any digit .* - followed by any character [0-9] -
	 * followed by any digit (so total 2) .* - followed by any character
	 */
	public String calculatePasswordStrength(String password) {

		// total score of password
		String iPasswordScore = "Password strength test failed.";
		// String password = argPassword.trim().replaceAll("([^0-9])", "");

		if (password.length() == 4) {
			if ("1234".equals(password) || "4321".equals(password)) {
				iPasswordScore = "Weak password error.";

			} else {
				long t1 = password.chars().filter(ch -> ch == '1').count();
				long t2 = password.chars().filter(ch -> ch == '2').count();
				long t3 = password.chars().filter(ch -> ch == '3').count();
				long t4 = password.chars().filter(ch -> ch == '4').count();
				long t5 = password.chars().filter(ch -> ch == '5').count();
				long t6 = password.chars().filter(ch -> ch == '6').count();
				long t7 = password.chars().filter(ch -> ch == '7').count();
				long t8 = password.chars().filter(ch -> ch == '8').count();
				long t9 = password.chars().filter(ch -> ch == '9').count();
				if (t1 > 2 || t2 > 2 || t3 > 2 || t4 > 2 || t5 > 2 || t6 > 2 || t7 > 2 || t8 > 2 || t9 > 2) {
					iPasswordScore = "No more than 2 digits should be the same.";

				} else {
					iPasswordScore = AppUtilities.PASSWORD_STRENGTH_SUCCESS;
				}
			}
		} else {
			iPasswordScore = "Password should have 4 digits.";

		}

		return iPasswordScore;

	}

	public String calculatePasswordStrength_V1(String argPassword) {

		// total score of password
		String iPasswordScore = "Password strength test failed.";
		String password = argPassword.trim();

		if (password.length() < 8) {
			return "PASSWORD SHOULD BE ATLEAST 8 CHARACTERS";
		} else {
			// if it contains one digit
			if (password.matches("(?=.*[0-9]).*")) {
				iPasswordScore = AppUtilities.PASSWORD_STRENGTH_SUCCESS;
			} else {
				return "PASSWORD SHOULD CONTAIN ATLEAST ONE NUMBER";
			}

			// if it contains one lower case letter
			if (password.matches("(?=.*[a-z]).*")) {
				iPasswordScore = AppUtilities.PASSWORD_STRENGTH_SUCCESS;
			} else {
				return "PASSWORD SHOULD CONTAIN ATLEAST ONE SMALL LETTER";
			}

			// if it contains one upper case letter
			if (password.matches("(?=.*[A-Z]).*")) {
				iPasswordScore = AppUtilities.PASSWORD_STRENGTH_SUCCESS;
			} else {
				return "PASSWORD SHOULD CONTAIN ATLEAST ONE CAPITAL LETTER";
			}

			// if it contains one special character
			if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
				iPasswordScore = AppUtilities.PASSWORD_STRENGTH_SUCCESS;
			} else {
				return "PASSWORD SHOULD CONTAIN ATLEAST ONE SPECIAL CHARACTER ("
						+ AppUtilities.PASSWORD_ALLOWED_SPECIAL_CHARACTERS + ")";
			}
		}

		return iPasswordScore;

	}

	private int calculatePasswordStrength_V2(String password) {

		int iPasswordScore = 0;

		if (password.length() < 8)
			return 0;
		else if (password.length() >= 10)
			iPasswordScore += 2;
		else
			iPasswordScore += 1;

		/*
		 * if password contains 2 digits, add 2 to score. if contains 1 digit
		 * add 1 to score
		 */
		if (password.matches("(?=.*[0-9].*[0-9]).*"))
			iPasswordScore += 2;
		else if (password.matches("(?=.*[0-9]).*"))
			iPasswordScore += 1;

		// if password contains 1 lower case letter, add 2 to score
		if (password.matches("(?=.*[a-z]).*"))
			iPasswordScore += 2;

		/*
		 * if password contains 2 upper case letters, add 2 to score. if
		 * contains only 1 then add 1 to score.
		 */
		if (password.matches("(?=.*[A-Z].*[A-Z]).*"))
			iPasswordScore += 2;
		else if (password.matches("(?=.*[A-Z]).*"))
			iPasswordScore += 1;

		/*
		 * if password contains 2 special characters, add 2 to score. if
		 * contains only 1 special character then add 1 to score.
		 */
		if (password.matches("(?=.*[~!@#$%^&*()_-].*[~!@#$%^&*()_-]).*"))
			iPasswordScore += 2;
		else if (password.matches("(?=.*[~!@#$%^&*()_-]).*"))
			iPasswordScore += 1;

		return iPasswordScore;

	}
	
	public Double generateThreeDigitRandomNumbers(Integer max, Integer min) {
		return Math.floor(Math.random() * (max - min) + min);
	}

	public static void main(String[] args) {
		/*
		 * CommonFunctions c = new CommonFunctions(); String jsonString =
		 * "{\"response_code\": \"000\",\"agent_id\": \"23\",\"response_status\": true,\"response_message\": \"Success\",\"Data\": {\"custRef\": \"21245548\",\"area\": \"Kajjansi\",\"notes\": \"John Smith\",\"request_id\": \"T020714493419639C72\",\"name\": \"John Smith\",\"balance\": \"15000\"}}"
		 * ;
		 * 
		 * System.out.println("obj: " + c.traverseJsonObjectObject(jsonString));
		 * 
		 * String someString = "1234"; long c1 = someString.chars().filter(ch ->
		 * ch == '1').count(); System.out.println("1234 -> count of 1 = " + c1);
		 * 
		 * long c2 = someString.chars().filter(ch -> ch == '1').count();
		 * System.out.println("1134 -> count of 1 = " + c2);
		 * 
		 * long c3 = someString.chars().filter(ch -> ch == '1').count();
		 * System.out.println("1114 -> count of 1 = " + c3);
		 * 
		 * long c4 = someString.chars().filter(ch -> ch == '1').count();
		 * System.out.println("1214 -> count of 1 = " + c4);
		 * 
		 * long c5 = someString.chars().filter(ch -> ch == '1').count();
		 * System.out.println("1231 -> count of 1 = " + c5);
		 * 
		 * long c6 = someString.chars().filter(ch -> ch == '1').count();
		 * System.out.println("1214 -> count of 1 = " + c6);
		 * 
		 * long c7 = someString.chars().filter(ch -> ch == '1').count();
		 * System.out.println("0234 -> count of 1 = " + c7);
		 */

		/*
		 * String districtId = ""; String territoryId = ""; String agentId =
		 * "1056"; String agentCode = districtId + territoryId + agentId; final
		 * int AGENT_CODE_LEN = 6; int agentCodeLen = agentCode.length();
		 * 
		 * System.out.println("Initial agentCode: " + agentCode); if
		 * (agentCodeLen != AGENT_CODE_LEN) { if (agentCodeLen > AGENT_CODE_LEN)
		 * { agentCode = agentCode.substring(agentCodeLen - AGENT_CODE_LEN,
		 * agentCodeLen); } else if (agentCodeLen < AGENT_CODE_LEN) { while
		 * (agentCodeLen < AGENT_CODE_LEN) { agentCode = "0" + agentCode;
		 * agentCodeLen = agentCode.length(); } } }
		 * 
		 * System.out.println("Final agentCode: " + agentCode);
		 * 
		 */
		// final JsonObject oldObj = new JsonObject();
		// oldObj.addProperty("key1", "");
		// oldObj.addProperty("key2", 34);
		// oldObj.addProperty("key3", "Team");
		// oldObj.addProperty("key4", 78);
		// oldObj.addProperty("key5", false);
		//
		// final JsonObject newObj = new JsonObject();
		// newObj.addProperty("key1", "abc");
		// newObj.addProperty("key2", "tom");
		// newObj.addProperty("key3", "Team3");
		// newObj.addProperty("key4", 78);
		// newObj.addProperty("key5", true);
		//
		// compareJsonObjects(oldObj, newObj);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		for (int i = 0; i < 100; i++) {
			Date date = new Date();
			String dateStamp = sdf.format(date);

			System.out.println("Date::: " + dateStamp);
			System.out.println("Date::: " + dateStamp.substring(dateStamp.length() - 3));
		}

	}

}
