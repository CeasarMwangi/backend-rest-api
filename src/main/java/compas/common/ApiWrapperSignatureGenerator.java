package compas.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author user
 *
 */
public class ApiWrapperSignatureGenerator {

    public static String generateSignature(String username, String password, String requestType, String apiKey) {
        String signature;
        try {
            String msg = username + SHA1(password) + requestType;
            // String msg = credential.getMsisdn() + SHA1("6070") +
            // credential.getDeviceId();

            signature = getHmac(msg, apiKey);

            return signature;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                builder.append(
                        (0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return builder.toString();
    }

    private static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] textBytes = text.getBytes("UTF-8");
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    private static String hmacSha256(String value, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String type = "HmacSHA256";
        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), type);
        Mac mac = Mac.getInstance(type);
        mac.init(secret);
        byte[] bytes = mac.doFinal(value.getBytes());
        return bytesToHex(bytes);
    }

    private final static char[] hexArray = "0123456789abcdef".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    static String getHmac(String text, String key) {
        String hmac = null;
        try {
            hmac = hmacSha256(text, key);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return hmac;
    }

    private static String toHex(String value) {
        String res = Integer.toHexString(Integer.parseInt(value));
        return res;
    }
    
    public static void main(String[] args){
    	
    	String username = "PbuAgencyAPIWrapper#";
    	String password = "aLATgxk_0v7E0s4g";
    	String requestType = "";
    	String apiKey= "wOW0G8TwGfNLckQ";
    	String signature = generateSignature( username, password, requestType, apiKey);
    	
    	System.out.print("signature: "+signature);
    	
    }
}

