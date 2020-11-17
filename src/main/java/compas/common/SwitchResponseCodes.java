package compas.common;

public class SwitchResponseCodes {
    /*
Processed OK	00
Limit Exceeded	01
Invalid Account	02
Account Inactive	03
Low Balance	04
Invalid Card Record	05
Field Error	09
Duplicate Transaction	10
Database Error	13
Warm Card	14
Hot Card	15
Invalid Card Status	16
Invalid Currency Code	19
Invalid PIN	24
Card Expired	25
Invalid Transaction Code	38
Invalid Transaction Type	39
Expired PRN	40
PRN Already Transacted	41
PRN Canceled	42
Invalid PRN	43
Amount Not Equal to Tax Amount	44
Unknown Error	46
Invalid Amount	48
Internal Error	49
Invalid Host Mode	50
Invalid Host Status	50
Host Not Processing	51
Invalid Card Number	53
Invalid Response	54
FS Error Message	55
Host Comms Down	55
Host Not Found	55
Pending	56
Timedout	58
Host Reject	59
PIN Retries Exceeded	60
HSM Timedout	61
Per Transaction Limit Exceeded	66
Invalid Account Status	67
PIN Change Reject	69
Unknown Transaction Source	75
Message Format Error	80
Transaction Reversed	91
Permission Denied	94
Tran Key Error	95
Transaction Rejected	95

    */
    
    public static final String RC_FIELD_39_999_TIMEOUT = "999";
}
