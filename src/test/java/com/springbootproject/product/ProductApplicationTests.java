package com.springbootproject.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8181/realms/master"
})
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}

}
