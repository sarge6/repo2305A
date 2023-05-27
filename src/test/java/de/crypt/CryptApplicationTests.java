package de.crypt;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptApplicationTests {

	@Value("${mysqldb.key}")
	private String secretKey;
	@Value("${mysqldb.pwd}")
	private String encPassword;

	@Autowired
	AccessApplicationProps accessApplicationProps;

	public BasicCryptUtil basicCryptUtil =
			new BasicCryptUtil(new BasicTextEncryptor(), secretKey);

	@Test
	public void decryptTest() {
		Assertions.assertAll(
				//org.springframework.core.env.Environment.getProperty()
				() -> {
					String actual = accessApplicationProps.getApplicationPropByName("mysqldb.pwd");
					Assertions.assertEquals(encPassword, actual, "AccessApplicationProps failed!");
				},
				//@Value("${mysqldb.pwd}")
				() -> {
					String decPassword = basicCryptUtil.decryptText(encPassword);
					Assertions.assertEquals("mysqldbpassword", decPassword, "Decryption failed!");
				}
		);
	}

}
