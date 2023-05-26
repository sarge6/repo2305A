package de.crypt;

import lombok.Data;
import org.jasypt.util.text.BasicTextEncryptor;
@Data
public class BasicCryptUtil {
    private static final String PASSWORD_DEFAULT = "secret";
    public final BasicTextEncryptor basicTextEncryptor;
    public String encryptText(String password, String text){
        if(password.isBlank()) password = PASSWORD_DEFAULT;
        basicTextEncryptor.setPassword(password);
        return basicTextEncryptor.encrypt(text);
    }
    public String decryptText(String password, String text){
        if(password.isBlank()) password = PASSWORD_DEFAULT;
        basicTextEncryptor.setPassword(password);
        return basicTextEncryptor.decrypt(text);
    }
}
