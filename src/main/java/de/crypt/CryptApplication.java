package de.crypt;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptApplication.class, args);
	}
	@Bean
	public CommandLineRunner runCLI(){
		return (args) -> {
			String pwd = "secret";

			BasicTextEncryptor bte = new BasicTextEncryptor();
			BasicCryptUtil basicCryptUtil1 = new BasicCryptUtil(bte, pwd);
			basicCryptUtil1.setBasicTextEncryptor(bte);
			basicCryptUtil1.setPassword(pwd);

			String plaintext = "plaintext!";
			String enctext = basicCryptUtil1.encryptText(plaintext);
			String dectext = basicCryptUtil1.decryptText(enctext);

			String msg = String.format("PASSWORD: %s%nPLAINTEXT: %s%nENCRYPTED: %s%nDECRYPTED: %s",
					pwd, plaintext, enctext, dectext);

			System.out.println("\n==================================\n");
			System.out.println(msg);
		};
	}

}
