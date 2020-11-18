package backendrestapi.logger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 5/30/2019.
 */
public class GenericCard {
    //********************************
//    @NotNull(message = "Please enter request_signature.")
//    @Size(min = 1, message = "request_signature should be more than {min} characters.")
//    private String request_signature;

    @NotNull(message = "Please enter cardPinBlock.")
    @Size(min = 1, message = "cardPinBlock should be more than {min} characters.")
    private String cardPinBlock;

    @NotNull(message = "Please enter cardTrack2Data.")
    @Size(min = 1, message = "cardTrack2Data should be more than {min} characters.")
    private String cardTrack2Data;

    @NotNull(message = "Please enter cardPAN.")
    @Size(min = 1, message = "cardPAN should be more than {min} digits.")
//    @Pattern(regexp = "[0-9]+", message = "Wrong cardPAN regexp.")
    private String cardPAN;
    
    private String cardICCData;
    private String cardSecurityInfo;
    private String CardTrack3;
    private String CardSeqNo;
    private String cardTrack1Data;
    private String cardExpiryDate;
    
    /*
     * '[0-9]+'
     * '[0-9.]+'
     * '[0-9.]*'
     * When within the brackets [ and ], the dot is considered a character by itself. 
     * So the second regex will match 0-9 as well as the decimal dot. 
     * The brackets denote a character set and will match one of the 
     * characters in the set (which is why the . is considered a character and not a specifier).
     */
    
    
    @NotNull(message = "Please enter Agent Float Account.")
    @Pattern(regexp = "[0-9]+", message = "Wrong Agent Float Account.")
    public String agentFloatAccount;
    
    @NotNull(message = "Please enter charges.")
    private Boolean charges;
    
    @NotNull(message = "Please enter requestType.")
    @Size(min = 1, message = "requestType should be more than {min} characters.")
    @Pattern(regexp = "[A-Z_]+", message = "Wrong requestType regexp.")
    private String requestType;

    //@Pattern(regexp = "(^[1-9])[0-9]+", message = "Wrong agent_id regexp.")
    @NotNull(message = "Please enter agent_id.")
    @Pattern(regexp = "(^[1-9])[0-9]*", message = "Wrong agent_id regexp.")
    public String agent_id;
    
    //@Pattern(regexp = "(^[1-9])[0-9]+", message = "Wrong REQUEST_AGENT_CODE regexp.")
    @NotNull(message = "Please enter REQUEST_AGENT_CODE.")
    @Pattern(regexp = "[0-9]+", message = "Wrong REQUEST_AGENT_CODE regexp.")
    public String REQUEST_AGENT_CODE;

    @Pattern(regexp = "(^[1-9])[0-9]*", message = "Wrong agent_id regexp.")
    public String validation_agent_id;

    @NotNull(message = "Please enter operational_id.")
    public Integer operational_id;



    //********************************





    public Integer transaction_type_id;
    public Integer mode_id;
    
    

    public String reference_account;
    public Double agent_commision;
    public Double bank_income;
    public Double excise_duty;
    public String charge;
    private Double psp_income;
    public String card_iccid;

    public Integer auth_mode;
    public String authentication;
    public String transaction_date;
    public String receipt_number;
    public String cbs_trans_id;
    public String narration;
    public Integer currency_id;
    public String latitude;
    public String longitude;
    public String macaddress;
    public String phone;
    private String finnacle_response_message;
    private String [] accounts_list;
    public String SchemeCode;
    public String utility_code;
    public String original_transId;
    public String terminal_trans_id;
    private Integer created_by;
    public String  status;
    public String  email;
    public String  loantype;
    public String security;
    public String CustRef;
    public String student_name;
    public boolean agentcommonly;
    private String requestAction;

    public GenericCard(){

    }
    
    

//    public String getRequest_signature() {
//        return request_signature;
//    }
//
//    public void setRequest_signature(String request_signature) {
//        this.request_signature = request_signature;
//    }







	public String getCardPinBlock() {
        return cardPinBlock;
    }

    public String getCharge() {
		return charge;
	}



	public void setCharge(String charge) {
		this.charge = charge;
	}



	public String getAgentFloatAccount() {
		return agentFloatAccount;
	}

	public void setAgentFloatAccount(String agentFloatAccount) {
		this.agentFloatAccount = agentFloatAccount;
	}

	public void setCardPinBlock(String cardPinBlock) {
        this.cardPinBlock = cardPinBlock;
    }

    public String getCardTrack2Data() {
        return cardTrack2Data;
    }

    public void setCardTrack2Data(String cardTrack2Data) {
        this.cardTrack2Data = cardTrack2Data;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public void setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
    }

    public String getCardICCData() {
        return cardICCData;
    }

    public void setCardICCData(String cardICCData) {
        this.cardICCData = cardICCData;
    }

    public String getCardSecurityInfo() {
        return cardSecurityInfo;
    }

    public void setCardSecurityInfo(String cardSecurityInfo) {
        this.cardSecurityInfo = cardSecurityInfo;
    }

    public String getCardTrack3() {
        return CardTrack3;
    }

    public void setCardTrack3(String cardTrack3) {
        CardTrack3 = cardTrack3;
    }

    public String getCardSeqNo() {
        return CardSeqNo;
    }

    public void setCardSeqNo(String cardSeqNo) {
        CardSeqNo = cardSeqNo;
    }

    public String getCardTrack1Data() {
        return cardTrack1Data;
    }

    public void setCardTrack1Data(String cardTrack1Data) {
        this.cardTrack1Data = cardTrack1Data;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public Integer getOperational_id() {
        return operational_id;
    }

    public void setOperational_id(Integer operational_id) {
        this.operational_id = operational_id;
    }

    public Integer getTransaction_type_id() {
        return transaction_type_id;
    }

    public void setTransaction_type_id(Integer transaction_type_id) {
        this.transaction_type_id = transaction_type_id;
    }

    public Integer getMode_id() {
        return mode_id;
    }

    public void setMode_id(Integer mode_id) {
        this.mode_id = mode_id;
    }



    public String getReference_account() {
        return reference_account;
    }

    public void setReference_account(String reference_account) {
        this.reference_account = reference_account;
    }


    public Double getAgent_commision() {
        return agent_commision;
    }

    public void setAgent_commision(Double agent_commision) {
        this.agent_commision = agent_commision;
    }

    public Double getBank_income() {
        return bank_income;
    }

    public void setBank_income(Double bank_income) {
        this.bank_income = bank_income;
    }

    public Double getExcise_duty() {
        return excise_duty;
    }

    public void setExcise_duty(Double excise_duty) {
        this.excise_duty = excise_duty;
    }

    public Double getPsp_income() {
        return psp_income;
    }

    public void setPsp_income(Double psp_income) {
        this.psp_income = psp_income;
    }

    public String getCard_iccid() {
        return card_iccid;
    }

    public void setCard_iccid(String card_iccid) {
        this.card_iccid = card_iccid;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public Integer getAuth_mode() {
        return auth_mode;
    }

    public void setAuth_mode(Integer auth_mode) {
        this.auth_mode = auth_mode;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getReceipt_number() {
        return receipt_number;
    }

    public void setReceipt_number(String receipt_number) {
        this.receipt_number = receipt_number;
    }

    public String getCbs_trans_id() {
        return cbs_trans_id;
    }

    public void setCbs_trans_id(String cbs_trans_id) {
        this.cbs_trans_id = cbs_trans_id;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Integer getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Integer currency_id) {
        this.currency_id = currency_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFinnacle_response_message() {
        return finnacle_response_message;
    }

    public void setFinnacle_response_message(String finnacle_response_message) {
        this.finnacle_response_message = finnacle_response_message;
    }

    public String[] getAccounts_list() {
        return accounts_list;
    }

    public void setAccounts_list(String[] accounts_list) {
        this.accounts_list = accounts_list;
    }

    public String getSchemeCode() {
        return SchemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        SchemeCode = schemeCode;
    }

    public String getUtility_code() {
        return utility_code;
    }

    public void setUtility_code(String utility_code) {
        this.utility_code = utility_code;
    }

    public String getOriginal_transId() {
        return original_transId;
    }

    public void setOriginal_transId(String original_transId) {
        this.original_transId = original_transId;
    }

    public String getTerminal_trans_id() {
        return terminal_trans_id;
    }

    public void setTerminal_trans_id(String terminal_trans_id) {
        this.terminal_trans_id = terminal_trans_id;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getCustRef() {
        return CustRef;
    }

    public void setCustRef(String custRef) {
        CustRef = custRef;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public boolean isAgentcommonly() {
        return agentcommonly;
    }

    public void setAgentcommonly(boolean agentcommonly) {
        this.agentcommonly = agentcommonly;
    }

    public String getValidation_agent_id() {
        return validation_agent_id;
    }

    public String getRequestAction() {
        return requestAction;
    }

    public void setRequestAction(String requestAction) {
        this.requestAction = requestAction;
    }

    public void setValidation_agent_id(String validation_agent_id) {
        this.validation_agent_id = validation_agent_id;
    }


	public Boolean getCharges() {
		return charges;
	}

	public void setCharges(Boolean charges) {
		this.charges = charges;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getREQUEST_AGENT_CODE() {
		return REQUEST_AGENT_CODE;
	}

	public void setREQUEST_AGENT_CODE(String rEQUEST_AGENT_CODE) {
		REQUEST_AGENT_CODE = rEQUEST_AGENT_CODE;
	}
	
    
}

