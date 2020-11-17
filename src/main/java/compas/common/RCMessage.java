package compas.common;

/**
 * Created by Ceasar on 06/05/2019.
 */
public class RCMessage {
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
	
	//116 is insufficient funds and 114 is invalid account
	
	public static final String RESPONSE_MSG_000 = "Success";
	public static final String RESPONSE_MSG_303 = "General error";
	public static final String RESPONSE_MSG_155 = "Unknown credentials supplied/ user credentials blocked";
	public static final String RESPONSE_MSG_404 = "Unknown request action";
//	public static final String RESPONSE_MSG_114 = "Invalid Account";
	public static final String RESPONSE_MSG_116 = "Insufficient funds";
	public static final String RESPONSE_MSG_911 = "Transaction timed out";
	public static final String RESPONSE_MSG_154 = "Transaction not found/Teller acct not found";
	public static final String RESPONSE_MSG_156 = "Missing required fields";
	public static final String RESPONSE_MSG_157 = "Transaction failed";
	public static final String RESPONSE_MSG_149 = "Duplicate transaction ID";
	public static final String RESPONSE_MSG_139 = "Student not found";
	public static final String RESPONSE_MSG_148 = "Part payment not allowed";
	
}
