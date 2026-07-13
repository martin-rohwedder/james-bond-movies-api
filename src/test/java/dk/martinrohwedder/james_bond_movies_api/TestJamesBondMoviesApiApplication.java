package dk.martinrohwedder.james_bond_movies_api;

import org.springframework.boot.SpringApplication;

public class TestJamesBondMoviesApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(JamesBondMoviesApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
