package compas.common;


import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.util.Base64;
/**
 *
 * @author mceasar
 */
@Component
public class RSARequestDecryptor {
	private Logger logger = LoggerFactory.getLogger(RSARequestDecryptor.class);


    //    private static final String PRIVATE_KEY_FILE = "/srv/applications/EAP-6.4.0/externalConfigs/InstaPay/keys/22_9_2017/private_1.key";
//    private static final String PRIVATE_KEY_FILE = "/srv/applications/EAP-6.4.0/externalConfigs/InstaPay/keys/Test2/rsa_1024_priv.pem";
    private static final String PRIVATE_KEY_FILE = "C:\\srv\\applications\\externalConfigs\\PBU_AgencySwitchAdaptor\\RSA\\rsa_1024_priv.pem";
//    private final String PUBLIC_KEY_FILE = "/srv/applications/EAP-6.4.0/externalConfigs/InstaPay/keys/22_9_2017/public_1.key";
    private final String PUBLIC_KEY_FILE = "C:\\srv\\applications\\externalConfigs\\PBU_AgencySwitchAdaptor\\RSA\\rsa_1024_pub.pem";

    private final String PLAIN_TEXT_ENCODING = "UTF-8";

    
    public static void main(String[] args) {

        RSARequestDecryptor obj1 = new RSARequestDecryptor();
        String plainText = "Test Message";

        try {
            //PrivateKey privateKey = obj1.readPrivateKeyFromFile(PRIVATE_KEY_FILE);
            //obj1.generateKeyPair();
            //obj1.decrypt(ciphertext, privateKey);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("done....");

        try{
            RSARequestDecryptor obj2 = new RSARequestDecryptor();


            PrivateKey privateKey = obj2.pemFileLoadPrivateKeyPkcs1OrPkcs8Encoded(new File(obj2.PRIVATE_KEY_FILE));

//            String plainText = obj2.decrypt(ciphertext, privateKey);
            
            String encryptedMessage = obj2.encryptMessage(plainText);
            String plainText2 = obj2.doDecryptMessage(encryptedMessage);
            
            System.out.println("plainText::: "+plainText);
            System.out.println("encryptedMessage::: "+encryptedMessage);
            System.out.println("plainText2::: "+plainText2);


        }catch(Exception ex){
            ex.printStackTrace();

        }
    }

    

    public String doDecryptMessage(String cipherText) {
        String plainText = null;
//        String ciphertext = "Dx3oOe/iYXNMpPN0/0ptLye40rv7kzlR5bMXGWohqYNe5wIOUwPRHd7aZa1PWcBxl3AYqM7BP/mofaOKQGpMzmLWtdrcqtObm/BJ9vNuRIT8d5JRdT5GH6SFT8LJtWAmJBaaSx2Ba8T1NsIkioZbqAjLJ4j4zs8eYcgZyRNvwUw=";

//        String ciphertext = "qzraJcN5bciUAQCklP/hVKZQYfUDfYkXUpWMJ6kZutHSRLQoVXcNGc0iOx7rh1/Yhi2+gwSItg54pnXiUQvZeyT88na570duP88cdsuG5qqv6RWUHQRSVvsmtkWAX1fGo2nXbutc8o8W6y+aPPgzg1lJKbBmVccmPGWZFPy4ebA=";


        try {
            //PrivateKey privateKey = obj1.readPrivateKeyFromFile(PRIVATE_KEY_FILE);
            //obj1.generateKeyPair();
            //obj1.decrypt(ciphertext, privateKey);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try{

            PrivateKey privateKey = pemFileLoadPrivateKeyPkcs1OrPkcs8Encoded(new File(PRIVATE_KEY_FILE));

            plainText = decrypt(cipherText, privateKey);
            
            logger.info("cipherText::: "+cipherText);
            logger.info("plainText::: "+plainText);
            


        }catch(Exception ex){
            ex.printStackTrace();
            String errorMessage = AppUtilities.logPreString() + AppUtilities.ERROR + ex.getMessage()
			+ AppUtilities.STACKTRACE + AppUtilities.getExceptionStacktrace(ex);
            ex.printStackTrace();
            logger.info(errorMessage);

        }
        return plainText;
    }

    public void generateKeyPair() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, FileNotFoundException, IOException {
        // Key Generation
        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory fact = KeyFactory.getInstance("RSA");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024); // 1024 used for normal

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
//Code to store the private key in file

        RSAPrivateKeySpec rsaPrivKeySpec = fact.getKeySpec(privateKey,
                RSAPrivateKeySpec.class);
        System.out.println("Writing private key...");
        fos = new FileOutputStream(PRIVATE_KEY_FILE);
        oos = new ObjectOutputStream(new BufferedOutputStream(fos));
        oos = new ObjectOutputStream(new BufferedOutputStream(fos));
        oos.writeObject(rsaPrivKeySpec.getModulus());
        oos.writeObject(rsaPrivKeySpec.getPrivateExponent());
        oos.close();

        System.out.println("Writing public key...");
        rsaPrivKeySpec = fact.getKeySpec(privateKey,
                RSAPrivateKeySpec.class);
        fos = new FileOutputStream(PUBLIC_KEY_FILE);
        oos = new ObjectOutputStream(new BufferedOutputStream(fos));
        oos = new ObjectOutputStream(new BufferedOutputStream(fos));
        oos.writeObject(rsaPrivKeySpec.getModulus());
        oos.writeObject(rsaPrivKeySpec.getPrivateExponent());
        oos.close();
    }

    public String decrypt(String ciphertext, PrivateKey privateKey)
            throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, 
            NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        if (ciphertext.length() == 0) {
            return null;
        }
        byte[] dec = org.apache.commons.codec.binary.Base64.decodeBase64(ciphertext);
//        try {
//            System.out.println("Private Key file name----" + PRIVATE_KEY_FILE);
//            privateKey = readPrivateKeyFromFile(PRIVATE_KEY_FILE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(dec);
        return new String(decrypted, PLAIN_TEXT_ENCODING);
    }

    /**
     *
     */
    public PrivateKey rreadPrivateKeyFromFile(String fileName)
            throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(fileName));
            ois = new ObjectInputStream(fis);
            System.out.println("Private Key file-" + fileName);

            BigInteger modulus = (BigInteger) ois.readObject();
            BigInteger exponent = (BigInteger) ois.readObject();

            // Get Private Key
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);
            return privateKey;
        } catch (Exception ex) {
        	String errorMessage = AppUtilities.logPreString() + AppUtilities.ERROR + ex.getMessage()
			+ AppUtilities.STACKTRACE + AppUtilities.getExceptionStacktrace(ex);
            ex.printStackTrace();
            logger.info(errorMessage);
        } finally {
            if (ois != null) {
                ois.close();
                if (fis != null) {
                    fis.close();
                }
            }
        }
        return null;
    }



    public static PrivateKey pemFileLoadPrivateKeyPkcs1OrPkcs8Encoded(File pemFileName) throws GeneralSecurityException, IOException {
        // PKCS#8 format
        final String PEM_PRIVATE_START = "-----BEGIN PRIVATE KEY-----";
        final String PEM_PRIVATE_END = "-----END PRIVATE KEY-----";

        // PKCS#1 format
        final String PEM_RSA_PRIVATE_START = "-----BEGIN RSA PRIVATE KEY-----";
        final String PEM_RSA_PRIVATE_END = "-----END RSA PRIVATE KEY-----";

        Path path = Paths.get(pemFileName.getAbsolutePath());

        String privateKeyPem = new String(Files.readAllBytes(path));

        if (privateKeyPem.indexOf(PEM_PRIVATE_START) != -1) { // PKCS#8 format
            privateKeyPem = privateKeyPem.replace(PEM_PRIVATE_START, "").replace(PEM_PRIVATE_END, "");
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            byte[] pkcs8EncodedKey = Base64.getDecoder().decode(privateKeyPem);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));

        } else if (privateKeyPem.indexOf(PEM_RSA_PRIVATE_START) != -1) {  // PKCS#1 format

            privateKeyPem = privateKeyPem.replace(PEM_RSA_PRIVATE_START, "").replace(PEM_RSA_PRIVATE_END, "");
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            DerInputStream derReader = new DerInputStream(Base64.getDecoder().decode(privateKeyPem));

            DerValue[] seq = derReader.getSequence(0);

            if (seq.length < 9) {
                throw new GeneralSecurityException("Could not parse a PKCS1 private key.");
            }

            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger prime1 = seq[4].getBigInteger();
            BigInteger prime2 = seq[5].getBigInteger();
            BigInteger exp1 = seq[6].getBigInteger();
            BigInteger exp2 = seq[7].getBigInteger();
            BigInteger crtCoef = seq[8].getBigInteger();

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);

            KeyFactory factory = KeyFactory.getInstance("RSA");

            return factory.generatePrivate(keySpec);
        }

        throw new GeneralSecurityException("Not supported format of a private key");
    }

    public String encryptMessage(String msg)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        try {
            PublicKey key = getPemPublicKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return org.apache.commons.codec.binary.Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
        }catch(Exception ex){
        	String errorMessage = AppUtilities.logPreString() + AppUtilities.ERROR + ex.getMessage()
			+ AppUtilities.STACKTRACE + AppUtilities.getExceptionStacktrace(ex);
            ex.printStackTrace();
            logger.info(errorMessage);
        }
    return null;
    }

//    public String encryptMessage(String plainText){
//        try {
//            BigInteger modulus = new BigInteger("C546BD0D85400B2C814A1C7EC98D3AB9C9DDEC1961546D050E3858503A0615DDEBA67CF1651BA85D88AEE99F9672261EFD67740962E921487BB70A0E371F1D6047360B46D0FFA8055936BD4C11C38D257A3BEABECD78629EAD9D5D770848C5C09610ADB02A0FBF117D92E74B7439B14131DCEB031D17FCCA3815356AFCBD0AA7", 16);
//            //0x10001
//            BigInteger pubExp = new BigInteger("010001", 16);
//
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(modulus, pubExp);
//            RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
//
//            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//
//            byte[] cipherData = cipher.doFinal(plainText.getBytes());
//            String encryptMessage = new String(cipherData, PLAIN_TEXT_ENCODING);
////            return encryptMessage;
//            return org.apache.commons.codec.binary.Base64.encodeBase64String(encryptMessage.getBytes());
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return null;
//    }

    public  PublicKey getPemPublicKey() throws Exception {
        File f = new File(PUBLIC_KEY_FILE);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        String temp = new String(keyBytes);
        String publicKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----\n", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");


        BASE64Decoder b64=new BASE64Decoder();
        byte[] decoded = b64.decodeBuffer(publicKeyPEM);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public  RSAPublicKey getPemPublicKey_2() throws Exception {

    //get the public key
    File file1 = new File(PUBLIC_KEY_FILE);
    FileInputStream fis1 = new FileInputStream(file1);
    DataInputStream dis1 = new DataInputStream(fis1);
    byte[] keyBytes1 = new byte[(int) file1.length()];
            dis1.readFully(keyBytes1);
            dis1.close();

        String temp = new String(keyBytes1);
        String publicKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----\n", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");

    X509EncodedKeySpec spec1 = new X509EncodedKeySpec(publicKeyPEM.getBytes());
    KeyFactory kf1 = KeyFactory.getInstance("RSA");
    RSAPublicKey pubKey = (RSAPublicKey) kf1.generatePublic(spec1);

//            logger.info("Exponent :" + pubKey.getPublicExponent());
//            logger.info("Modulus" + pubKey.getModulus());
            return pubKey;
    }

    
    
}

