package dk.martinrohwedder.james_bond_movies_api.configurations;

import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeMovies(MovieRepository movieRepository) {
        return args -> {
            if (movieRepository.count() == 0) {
                movieRepository.saveAll(List.of(
                        Movie.builder().title("Dr. No").shortDescription("A resourceful British government agent seeks answers in a case involving the disappearance of a colleague and the disruption of the American space program.").build(),
                        Movie.builder().title("From Russia With Love").shortDescription("James Bond willingly inserts himself into an assassination plot involving a naive Russian beauty in order to retrieve a Soviet encryption device that was stolen by the evil SPECTRE organization.").build()
                ));
            }
        };
    }
}
