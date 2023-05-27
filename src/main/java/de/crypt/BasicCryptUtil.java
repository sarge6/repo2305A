package de.crypt;

import lombok.Data;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

@Data
@Component
public class BasicCryptUtil {
    private static final String PASSWORD_DEFAULT = "secret";
    public BasicTextEncryptor basicTextEncryptor; // = new BasicTextEncryptor();
    public BasicCryptUtil(){}
    public BasicCryptUtil(BasicTextEncryptor basicTextEncryptor, String password){
        this.basicTextEncryptor = basicTextEncryptor;
        if(password.isBlank()){
            this.basicTextEncryptor.setPassword(PASSWORD_DEFAULT);
        }else{
            this.basicTextEncryptor.setPassword(password);
        }
    }
    public String encryptText(String text){
        return basicTextEncryptor.encrypt(text);
    }
    public String decryptText(String text){
        return basicTextEncryptor.decrypt(text);
    }
}
