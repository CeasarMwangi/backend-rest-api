package compas.common;

public class SwitchFieldsConfigs {
    /*
    for [DE-32 ] AcqInstIdCode: [627922], Len[6]

it will be to decide between  primarily the CMPX and PBU. for SIT, send any number of length 6
    
    [DE-41 ] CardAcceptTermId: [56845555], Len[8]

This field usually contain the Terminal id of POS machine or the ID of any device used to initiate transaction for future reference.. for SIT send any .
    
    same for [DE-42 ] CardAccptIdCode: [050147717], Len[9]
    */
    
    public static final String ISO_FIELD_DE_32 = "627922";
    public static final String ISO_FIELD_DE_41 = "56845555";
    public static final String ISO_FIELD_DE_42 = "050147717";
        /*
    i have issue with UBA and SABC as used in that message. These refers to UBA integration. What about for COMPAS what should i use?
You will use COMPASS or CMPX (for short if you want to)
    */
    //Card acceptor name/location (1-23 street address, 24-36 city, 37-38 state, 39-40 country)
    public static final String ISO_FIELD_DE_43 = "CMPX";
}
