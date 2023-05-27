package de.crypt;

//import org.hibernate.cfg.Environment;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CryptApplication {

	@Value("${mysqldb.key}")
	String mysqldb_key;

	@Value("${mysqldb.pwd}")
	String mysqldb_pwd;

	@Autowired
	Environment environment;
	//Environment environment = applicationContext.getBean(Environment.class);
	//        String propertyValue = environment.getProperty("any.property.from.configuration")


	public static String cryptCredentials(String key, String text, boolean decrypt){
		BasicTextEncryptor bte = new BasicTextEncryptor();
		BasicCryptUtil basicCryptUtil1 = new BasicCryptUtil(bte, key);
		String resulttext = "";
		String resulttext_rev = "";
		if(!decrypt){
			resulttext = basicCryptUtil1.encryptText(text);
			resulttext_rev = basicCryptUtil1.decryptText(resulttext);
		}else{
			resulttext = basicCryptUtil1.decryptText(text);
			resulttext_rev = basicCryptUtil1.encryptText(resulttext);
		}
		System.out.println("\n==================================\n");
		String msg = String.format("KEY: %s%nTEXT: %s%nDECRYPT:%b%nRESULT: %s",
				key, text, decrypt, resulttext);
		System.out.println(msg);
		System.out.println("REVERSE: " + resulttext_rev);
		return resulttext;
	}

	public static void main(String[] args) {
		SpringApplication.run(CryptApplication.class, args);
	}
	@Bean
	public CommandLineRunner runCLI(){
		return (args) -> {

			System.out.println("@Value Params: " + mysqldb_key + "\t" + mysqldb_pwd);

			String pwd = environment.getProperty("mysqldb.pwd");
			String key = environment.getProperty("mysqldb.key");
			System.out.println("Environments: " + key + "\t" + pwd);


			String result = cryptCredentials("secret", "mysqldbpassword", false);

			cryptCredentials("secret", result, true);

			cryptCredentials("secret", "VR3HtsZm1+jIbNoT7SXaCfEMXPYvAbmu", true);



		};
	}

}
