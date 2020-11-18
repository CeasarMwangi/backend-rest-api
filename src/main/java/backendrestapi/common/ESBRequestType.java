package backendrestapi.common;

/**
 * Created by Mceasar on 7/26/2019.
 */

public class ESBRequestType {
	
    //CWD
    public static final String CWD_USING_OTP = "CWD_USING_OTP";
    public static final String CWD_USING_BIO = "CWD_USING_BIO";
    public static final String CWD_USING_ATMCARD = "CWD_USING_ATMCARD" ;

    
  //Bouquet

    public static final String BILL_PRESENTMENT_GET_GOTV_BOUQUET_LIST = "BILL_PRESENTMENT_GET_GOTV_BOUQUET_LIST" ;
    public static final String BILL_PRESENTMENT_GET_DSTV_BOUQUET_LIST = "BILL_PRESENTMENT_GET_DSTV_BOUQUET_LIST" ;


    
    public static final String PAY_STARTIMES_USING_CASH = "PAY_STARTIMES_USING_CASH" ;

    //School fees
    public static final String PAY_SCHOOL_FEES_USING_CASH = "PAY_SCHOOL_FEES_USING_CASH" ;

    //Airtime
    //MTN
    public static final String BUY_MTN_AIRTIME_USING_ATMCARD = "BUY_MTN_AIRTIME_USING_ATMCARD" ;
    public static final String BUY_MTN_AIRTIME_USING_CASH = "BUY_MTN_AIRTIME_USING_CASH" ;
    //Airtel
    public static final String BUY_AIRTEL_AIRTIME_USING_ATMCARD = "BUY_AIRTEL_AIRTIME_USING_ATMCARD" ;
    public static final String BUY_AIRTEL_AIRTIME_USING_CASH = "BUY_AIRTEL_AIRTIME_USING_CASH" ;

    
   
	//Account lookup
	public static final String ACCOUNT_LOOKUP_USING_ATM_CARD = "ACCOUNT_LOOKUP_USING_ATM_CARD" ;
	public static final String ACCOUNT_LOOKUP_USING_ACCOUNT_NO = "ACCOUNT_LOOKUP_USING_ACCOUNT_NO" ;
	
    //NWSC
    public static final String BILLPAY_NWSC_USING_CASH = "BILLPAY_NWSC_USING_CASH" ;
    public static final String BILL_PRESENTMENT_NWSC_GET_AREAS= "BILL_PRESENTMENT_NWSC_GET_AREAS" ;
    public static final String BILL_PRESENTMENT_NWSC_VALIDATION= "BILL_PRESENTMENT_NWSC_VALIDATION" ;

    
	
    //URA
    public static final String PAY_URA_USING_CASH = "PAY_URA_USING_CASH" ;
    public static final String BILL_PRESENTMENT_URA_GET_BRANCHES = "BILL_PRESENTMENT_URA_GET_BRANCHES" ;
    public static final String BILL_PRESENTMENT_URA_VALIDATE = "BILL_PRESENTMENT_URA_VALIDATE" ;

	//FULL
    public static final String FULL_STATEMENT_USING_ATMCARD = "FULL_STATEMENT_USING_ATMCARD" ;
    public static final String FULL_STATEMENT_USING_BIO = "FULL_STATEMENT_USING_BIO" ;
    public static final String FULL_STATEMENT_USING_OTP = "FULL_STATEMENT_USING_OTP" ;


    //BI
    public static final String BI_USING_ATMCARD = "BI_USING_ATMCARD" ;
    public static final String AGENT_FLOAT_BI = "AGENT_FLOAT_BI" ;
//    public static final String BALANCE_INQUIRY = "BI";
    public static final String BI_USING_BIO = "BI_USING_BIO";
    public static final String BI_USING_OTP = "BI_USING_OTP";
    
    //Mini statement
    public static final String MINI_STATEMENT_USING_ATMCARD = "MINI_STATEMENT_USING_ATMCARD" ;
    public static final String WALLET_TRANSFERS_USING_ATMCARD = "WALLET_TRANSFERS_USING_ATMCARD" ;
    public static final String MINI_STATEMENT_USING_OTP = "MINI_STATEMENT_USING_OTP" ;
//    public static final String MINISTATEMENT = "MINI";
    public static final String MINI_STATEMENT_USING_BIO = "MINI_STATEMENT_USING_BIO" ;

    //FT - INTRA
    public static final String INTRA_BANK_FT_USING_ATMCARD = "INTRA_BANK_FT_USING_ATMCARD" ;
    public static final String INTRA_BANK_FT_USING_OTP = "INTRA_BANK_FT_USING_OTP" ;
    public static final String INTRA_BANK_FT_USING_BIO = "INTRA_BANK_FT_USING_BIO" ;
    public static final String AGENT_FLOAT_TRANSFER = "AGENT_FLOAT_TRANSFER" ;

    //FT - INTER
    public static final String INTER_BANK_FT_USING_ATMCARD = "INTER_BANK_FT_USING_ATMCARD" ;
    public static final String INTER_BANK_FT_USING_OTP = "INTER_BANK_FT_USING_OTP" ;
    public static final String INTER_BANK_FT_USING_BIO = "INTER_BANK_FT_USING_BIO" ;
    
    //BANK_2_WALLET_TRANSFERS
    public static final String BANK_2_WALLET_TRANSFERS_USING_ATMCARD = "BANK_2_WALLET_TRANSFERS_USING_ATMCARD" ;
    public static final String BANK_2_WALLET_TRANSFERS_USING_OTP = "BANK_2_WALLET_TRANSFERS_USING_OTP" ;
    public static final String BANK_2_WALLET_TRANSFERS_USING_BIO = "BANK_2_WALLET_TRANSFERS_USING_BIO" ;

    
    //
    public static final String BILLPAY_NWSC_USING_ATMCARD = "BILLPAY_NWSC_USING_ATMCARD" ;
    public static final String UTILITY_PAY_USING_ATMCARD = "UTILITY_PAY_USING_ATMCARD" ;
    public static final String PAY_SCHOOL_FEES_USING_ATMCARD = "PAY_SCHOOL_FEES_USING_ATMCARD" ;
    
    //TV
    //AzamTv
    public static final String BILL_PRESENTMENT_GET_AZAMTV_PACKAGES = "BILL_PRESENTMENT_GET_AZAMTV_PACKAGES" ;
    public static final String BILL_PRESENTMENT_AZAMTV_VALIDATION = "BILL_PRESENTMENT_AZAMTV_VALIDATION" ;
    public static final String PAY_AZAMTV_USING_CASH = "PAY_AZAMTV_USING_CASH" ;
  //DSTV
    public static final String PAY_DSTV_USING_CASH = "PAY_DSTV_USING_CASH" ;
    public static final String PAY_DSTV_USING_ATMCARD = "PAY_DSTV_USING_ATMCARD" ;
    //GoTV
    public static final String PAY_GOTV_USING_CASH = "PAY_GOTV_USING_CASH" ;
    public static final String PAY_GOTV_USING_ATMCARD = "PAY_GOTV_USING_ATMCARD" ;
    
    //Umeme
    public static final String BILL_PRESENTMENT_UMEME_VALIDATION = "BILL_PRESENTMENT_UMEME_VALIDATION" ;
    public static final String PAY_UMEME_USING_CASH = "PAY_UMEME_USING_CASH" ;

    public static final String PAY_STARTIMES_USING_ATMCARD = "PAY_STARTIMES_USING_ATMCARD" ;
    //CDP
    public static final String CASH_DEPOSIT_USING_ATMCARD = "CASH_DEPOSIT_USING_ATMCARD" ;
    public static final String CASH_DEPOSIT = "CDP";
    
    
    public static final String REQUEST_LOGIN = "REQUEST_LOGIN" ;
    public static final String REQUEST_FETCH_USERS = "REQUEST_FETCH_USERS" ;
    public static final String REQUEST_CHANGE_PASSWORD = "REQUEST_CHANGE_PASSWORD" ;
    
    //Loan
    public static final String LOAN_REQUEST_USING_ATMCARD = "LOAN_REQUEST_USING_ATMCARD" ;
    public static final String LOAN_REQUEST_USING_OTP = "LOAN_REQUEST_USING_OTP" ;
    public static final String LOAN_REQUEST_USING_BIO = "LOAN_REQUEST_USING_BIO" ;
    



    
    public static final String CASH_WITHDRAWAL = "CWD";
    public static final String CASH_WITHDRAWAL_BIOMETRIC = "CWD_BIOMETRIC";
    public static final String CARDLESS_FULFILLMENT = "CLF";
    public static final String AIRTIME_TOPUP_CASH = "TPCS";
    public static final String AIRTIME_TOPUP_CARD = "TPCR";
    public static final String BILL_PAYMENT_CASH = "BPCS";
    public static final String BILL_PAYMENT_CARD = "BPCR";
    public static final String COLLECTIONS_CASH = "COCS";
    public static final String COLLECTIONS_CARD = "COCR";
    




    public static final String AGENT_FLOAT_REQUEST = "FLOAT_REQUEST";
    public static final String TRANSACTION_CHARGES = "TRANSACTION_CHARGES";
    //
    public static final String ATM_CARD_REQUEST_USING_BIO = "ATM_CARD__REQUEST_USING_BIO" ;
    public static final String ATM_CARD_REQUEST_USING_OTP = "CARD_REQUEST_USING_OTP" ;
    
    //
    public static final String AGENT_LAST_TRANSACTIONS = "AGENT_LAST_TRANSACTIONS" ;
    public static final String AGENT_LAST_TRANSACTIONS_COMMISSIONS = "AGENT_LAST_TRANSACTIONS_COMMISSIONS" ;



}
