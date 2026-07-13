package dk.martinrohwedder.james_bond_movies_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JamesBondMoviesApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
