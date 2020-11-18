package backendrestapi.common;

/**
 * 
 * @author Mceasar
 *
 */
public class RequestStatus {

	public static final String REQUEST_STATUS_PENDING = "PENDING";
	public static final String REQUEST_STATUS_FAILED = "FAILED";
	public static final String REQUEST_STATUS_SUCCESSFUL = "SUCCESSFUL";
	public static final String REQUEST_STATUS_PROCESSED = "PROCESSED";
	//{statusId:0,stat:"ALL"},{statusId:1,stat:"PENDING"},{statusId:2,stat:"PROCESSED"}
	public static final Integer ACCOOUNT_INITIATION_REQUEST_STATUS_ALL_0= 0;
	public static final Integer ACCOOUNT_INITIATION_REQUEST_STATUS_PENDING_1 = 1;
	public static final Integer ACCOOUNT_INITIATION_REQUEST_STATUS_PROCESSED_2 = 2;
	
	//{statusId:0,stat:"ALL"},{statusId:1,stat:"PENDING"},{statusId:2,stat:"PROCESSED"}
	public static final Integer LOAN_REQUEST_STATUS_ALL_0= 0;
	public static final Integer LOAN_REQUEST_STATUS_PENDING_1 = 1;
	public static final Integer LOAN_REQUEST_STATUS_PROCESSED_2 = 2;
	
	//{statusId:0,stat:"ALL"},{statusId:1,stat:"PENDING"},{statusId:2,stat:"PROCESSED"}
	public static final Integer CARD_REQUEST_STATUS_ALL_0= 0;
	public static final Integer CARD_REQUEST_STATUS_PENDING_1 = 1;
	public static final Integer CARD_REQUEST_STATUS_PROCESSED_2 = 2;
	
}
