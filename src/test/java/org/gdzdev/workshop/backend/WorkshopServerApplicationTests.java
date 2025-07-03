package org.gdzdev.workshop.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@PropertySource("file:./.env.test")
class WorkshopServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
