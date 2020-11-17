package compas.common;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import compas.Logger.ApiLogger;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.PrivateKey;
import java.security.PublicKey;

/*
 *  This has failed to work on the server but works on development PC
*/
@Component
public class ServerDecrypt {

	/*
	 *  This has failed to work on the server but works on development PC
    ApiLogger apiLogger = new ApiLogger();
    
	  static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMpk+t4vk8Z3W/rILMseSVqo136mduhxw7pxX5TNFN1vAtf4Z4aHozqHeAErl9FRPTMU/O9hGHut7xwpd89FVoDQ+OBsWfyP1tiKztb9LHfjAqbyA99Ldqj6CzBsx5RCJb2LmcJq+rcvGk6ose42TZdbI5PJimO0noJaAdRxxyqjAgMBAAECgYEAtnAt3ogeecDvzXARSIqlbq5RiZ1+A+tAllhJ4zS1wSCm/IyyrQao8UJZvxxPfmPveZNKqHDqg9n2dnk+wTxsSpbd8LeNjeItoPPTPafYTyJlwPdciBf/aSSzwL8gey7Ruu0KSGOOqNxprZVh6yupVNpfXIe+r52+RHAgRSTqGmkCQQD9SlUdUWNCfHikWwhMXMNjftqDJYeSgkDogc5/i11l+a/TPN4Pxy+npOBuwBek6QQpuMEX27hC3nJ/4+4HrZH1AkEAzI9DO3QqshqlXkAcfTnPbpJJwtB+yDlDyuE6kPdEctJE+i/7fBVe9pIsrY4mBQnCrRtRM2L3eA023VVgQyUzNwJBAJkAYdHH7BGpZqbBZo3aKxza79vwu6hAjjzkzHw3D0AeR5tuVSVqyNqXugqsdpvjNeSN+fYTf4bFIJ0obgytSFECQF1Z88CqHuHwrw74CwhK3ZMER40UQQd2uq1XY7bMYPxVYQ0b4JSb3B6CTslVWZxq++Cr2r6sp2qt/R+82pVJ4LkCQFC9/PY2pnqOsGcdAZncUMnOzCahhocqdbiTdTyg8ct6XYVrEokdwumBsqL5k36uUvsEJDHhB9x8DGGLTAPhkPU=";
	  
	  static String encryptedTextString = "l2R9p0Nr9Sffe3VL9lu6mHJ8myzsghd9s2dTtu4NsQA3GqpZkuMBy77gj+fLQew/19G5tuQz4QA4\n4Xx4ZkvVl7FrUqWxoSi5F/gaCOL5jpNMNjpPvgJIQaQu5hZFjdQ4e2S4jpnZrOXGeBdlyeHFvWmx\npVLevVq5UJnWRUblvKYM3jU48fC23gJ/n7dxbzvwTQt4ubbcOP8vlYAI7NgKSK50jBhK5YlEZxeA\nXE0V8bXzWoTnF4450tJ1lC/Tj4fSqEcRJp4RX0rCIDceOAPHwMem7IXGK8LeRYchyudAAjZqCpGJ\nQ9H05FJvoscn523QuQesw4XRH0uYE/dn6rDVIWi6/SzyDgrT8MUOFPu/hCvjM3vx894B2dMELb/t\nDkCLUZ9vrFWpNR5gZxmNMN4tmdr15Yf5IQaSy3EJEeiqR8uKD8r0HRhXmZlpopl6gDGSbgla+hwZ\n1skaho0XfJlSxUwamtjN3S8Rj66rod4SdIOuptJHYnAxX64pXu+eao4bFpINUwOdOEpagXxZAbrY\nnW5PmqaipvxxQMoiqwWceHdb0eoruQ8nAo6UaLuBflZjsqe8KmBOVqCnQ2021VwCJGnKUkJCWfmY\n3x6HdhdoO2LqIwL4525q85mm9r44pQJvHq3g+l6YGCluIN3OQAPNGsHYa14Eh1+BHlQzJpP0RDS5\nL4pkrRVYemMVMgJsm17EZQ1xmfIR2qzLB8sg/G1iX7K6GQRUcPp5rnBqObtoHTfu5PuPjLB986Tz\nCZjyYlsfpLfeTDkaC/QrCKSnQyKhZ2PBsNomUM3GwheSgowaKXUy3mSjMkh6x2vjbweHGicvXntd\n4CdDPBToieq1sO8JARGUN8PTI1jMru4gR+NAGJuFsGltHvGrQ++/zcxqX8ZpxXoGOqDWG0bCHgfg\n4za2sT7x9Z0+mluJzjyVPeJTksjTzH3HgqN1NkA4xDvYBH5Tl4Rs7ju4sEhJNCCSxVuLmtP46Gew\nGF9OfJLLYOpvjObGxQTuHzbODLo5sV6NmE0YBRWETwsZxcbQy2SvDDSSptq/Bsa572ZI0ku7OZi+\n7vluPPjF3a1SZjpElFmSvisoztO58jw+Mrspwk3DFzzhCzKREHzgVmYT8vu2GGW8KnqfZHe4SkSh\nCMmVhRVHv4qxxm/GrRCgwuC14fnB56kCRD0P0GSVz4GgkkAfVByn4YQ=\n";
	  static String encryptedSecretKeyString = "Jl08rWrGrhQcZdh2cZYyiiEGqB9Ty2Is1Zx3rW5aniT0wjyOWGt9WP2AUb2QGERZfUwU/g77m2EWUeLyyqxSy5MBTvRys+CxA/t93XjZ4L0/eAiX3ZsMkB9E7TAWxDsqqAXO6gg0DAgHKmhoGnyCKGGBSgsRcPwueT2A9L9aTOo=";

	  public static void main(String args[]) {

	    try {

	      // 1. Get private key
	      PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
	      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	      PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);

	      // 2. Decrypt encrypted secret key using private key
	      Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");   
	      cipher1.init(Cipher.DECRYPT_MODE, privateKey);  
	      byte[] secretKeyBytes = cipher1.doFinal(Base64.getDecoder().decode(encryptedSecretKeyString));
	      SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "AES");

	      // 3. Decrypt encrypted text using secret key
	      byte[] raw = secretKey.getEncoded();
	      SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
//	      byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedTextString));
	      byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(encryptedTextString));
	      String text = new String(original, Charset.forName("UTF-8"));

	      // 4. Print the original text sent by client
	      System.out.println("text\n" + text + "\n\n");

	    } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public String decryptUsingPrivateKeyAndSecretKey(String encryptedData) {
		  String plainText = "";
		    try {

		      // 1. Get private key
		      PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
		      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		      PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);

		      // 2. Decrypt encrypted secret key using private key
		      Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");   
		      cipher1.init(Cipher.DECRYPT_MODE, privateKey);  
		      byte[] secretKeyBytes = cipher1.doFinal(Base64.getDecoder().decode(encryptedSecretKeyString));
		      SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "AES");

		      // 3. Decrypt encrypted text using secret key
		      byte[] raw = secretKey.getEncoded();
		      SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		      cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
//		      byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedTextString));
		      byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(encryptedData));
		      
		      plainText = new String(original, Charset.forName("UTF-8"));

		    } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException ex) {
		    	 String errorMessage = AppUtilities.logPreString() + AppUtilities.ERROR + ex.getMessage()
	                + AppUtilities.STACKTRACE
	                + AppUtilities.getExceptionStacktrace(ex);
	        apiLogger.doLog(AppUtilities.LOG_FILE_EXCEPTIONS, errorMessage);
		    }
		    return plainText;
		  }
	  */
	}
