package de.crypt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptApplicationTests {

	@Value("")
	private String mysqldb_key;
	@Test
	void contextLoads() {

	}



}
