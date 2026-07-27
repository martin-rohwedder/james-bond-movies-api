package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@Testcontainers
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void should_find_all_movies() {
        List<Movie> movies = movieRepository.findAllByOrderByMovieNumberAsc();

        assertThat(movies).hasSize(25);
        assertThat(movies.getFirst()).extracting(Movie::getMovieNumber).isEqualTo(1);
        assertThat(movies.getLast()).extracting(Movie::getMovieNumber).isEqualTo(25);
    }
}
