package backendrestapi;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by gkatzioura on 10/5/16.
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    /*
    @Override
    public String encode(CharSequence rawPassword) {
        String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
        return hashed;
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
    */

    @Override
    public String encode(CharSequence rawPassword) {
        //System.out.println(AppUtilities.logPreString() +" rawPassword: "+rawPassword.toString());
        String encrypted =  "";//CustomAES.encrypt(rawPassword.toString());
        //System.out.println(AppUtilities.logPreString() +" encrypted: "+encrypted);
        return encrypted;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //System.out.println(AppUtilities.logPreString() +" rawPassword: "+rawPassword.toString());
        //System.out.println(AppUtilities.logPreString() +" encodedPassword: "+encodedPassword);
        String encrypted =  "";//CustomAES.encrypt(rawPassword.toString());
        //System.out.println(AppUtilities.logPreString() +" encrypted: "+encrypted);

        return encodedPassword.equals(encrypted);
    }
}

