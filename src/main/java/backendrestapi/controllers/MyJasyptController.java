package backendrestapi.controllers;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/jasypt-crypto")
public class MyJasyptController {

	@Value("${userdemo.name}")
	private String username;
	
	@Autowired
	@Qualifier("jasyptStringEncryptor")
	StringEncryptor stringEncryptor;
	
	private static String mpCryptoPassword = "techjava";
	private static String plainText = "QDckmA@2019)";

	@PostMapping(value = "/encrypt-decrypt")
	public String index() {
		System.out.println("##############################");
		
		System.out.println("Username is -------->" + username);

		System.out.println("##############################");

		test1();
		test2();
		test3();
		test4();
		

		String encryptedPassword = stringEncryptor.encrypt(plainText);
		System.out.println("5 => Encrypted: " + encryptedPassword);

		String decryptedPassword = stringEncryptor.decrypt(encryptedPassword);
		System.out.println("5 => Decrypted: " + decryptedPassword);
		
		return "123456";
	}

	public static void test1() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(mpCryptoPassword);
		String encryptedPassword = encryptor.encrypt(plainText);
		System.out.println("1 => encryptedPassword: " + encryptedPassword);

		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword(mpCryptoPassword);
		System.out.println("1 => decryptedPassword: " + decryptor.decrypt(encryptedPassword));
	}

	public static void test2() {

		try {
			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
			String encryptedPassword = encryptor.encryptPassword(plainText);

			if (encryptor.checkPassword(plainText, encryptedPassword)) {
				// correct
				System.out.println("Encrypted: " + encryptedPassword);
			} else {
				// bad again
				System.out.println("Error: ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test3() {

		try {
			StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
			textEncryptor.setPassword(mpCryptoPassword);
			String encryptedPassword = textEncryptor.encrypt(plainText);
			System.out.println("3 => Encrypted: " + encryptedPassword);

			String decryptedPassword = textEncryptor.decrypt(encryptedPassword);
			System.out.println("3 = > Decrypted: " + decryptedPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test4() {
		StringEncryptor encryptor = stringEncryptor();
		String encryptedPassword = encryptor.encrypt(plainText);
		System.out.println("4 => Encrypted: " + encryptedPassword);

		String decryptedPassword = encryptor.decrypt(encryptedPassword);
		System.out.println("4 => Decrypted: " + decryptedPassword);

	}
	
	
	public static StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(mpCryptoPassword);
		config.setAlgorithm("PBEWithMD5AndDES");
		 config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		 config.setProviderName("SunJCE");
		 config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		 config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		 config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}


}
