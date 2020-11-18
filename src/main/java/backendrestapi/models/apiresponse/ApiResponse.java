package backendrestapi.models.apiresponse;

import org.json.JSONArray;



/**
 * Created by CLLSDJACKT013 on 23/05/2018.
 */
public class ApiResponse {
    private String requestType;

    private String response_code;
    private String response_message;
    private Boolean response_status;
    private Boolean status;

    private String trn_receipt_number;
    private JSONArray requestErrors;
    private String stack_trace;

    private String amount;
    private String otpPassword;
    private String afisId;
    private Boolean isBioAuthenticated;
    private String chargeBurden;
    private String chargeType;
    
    ////////////////////
    private String OldTranId;



    //default constructor
    public ApiResponse(){}

    public ApiResponse(String response_code, String response_message, 
    		Boolean response_status) {
        this.response_code = response_code;
        this.response_message = response_message;
        this.response_status = response_status;

    }


	public String getOldTranId() {
		return OldTranId;
	}

	public void setOldTranId(String oldTranId) {
		OldTranId = oldTranId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeBurden() {
		return chargeBurden;
	}

	public void setChargeBurden(String chargeBurden) {
		this.chargeBurden = chargeBurden;
	}

	public Boolean getIsBioAuthenticated() {
		return isBioAuthenticated;
	}

	public void setIsBioAuthenticated(Boolean isBioAuthenticated) {
		this.isBioAuthenticated = isBioAuthenticated;
	}

	public String getAfisId() {
		return afisId;
	}

	public void setAfisId(String afisId) {
		this.afisId = afisId;
	}


    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

    public Boolean getResponse_status() {
        return response_status;
    }

    public void setResponse_status(Boolean response_status) {
        this.response_status = response_status;
    }




    public String getTrn_receipt_number() {
        return trn_receipt_number;
    }

    public void setTrn_receipt_number(String trn_receipt_number) {
        this.trn_receipt_number = trn_receipt_number;
    }

    public JSONArray getRequestErrors() {
        return requestErrors;
    }

    public void setRequestErrors(JSONArray requestErrors) {
        this.requestErrors = requestErrors;
    }
    

    public String getStack_trace() {
		return stack_trace;
	}

	public void setStack_trace(String stack_trace) {
		this.stack_trace = stack_trace;
	}
	
	

	
	

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}




	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	

	
	public String getOtpPassword() {
		return otpPassword;
	}

	public void setOtpPassword(String otpPassword) {
		this.otpPassword = otpPassword;
	}


}
