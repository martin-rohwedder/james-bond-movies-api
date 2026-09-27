package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.graphql.test.autoconfigure.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@Testcontainers
@SpringBootTest
@AutoConfigureGraphQlTester
@ActiveProfiles("test")
public class GraphQLScalarIntegrationTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GraphQlTester graphQlTester;

    private Movie getSingleMovie() {
        return movieRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Test database contains no movies")
                );
    }

    @Test
    void shouldSerializeLocalDate() {
        Movie movie = getSingleMovie();

        graphQlTester
                .document("""
                        query GetMovie($id: ID!) {
                            movie(id: $id) {
                                releaseDates {
                                    dateOfRelease
                                }
                            }
                        }
                        """)
                .variable("id", movie.getId().toString())
                .execute()
                .path("movie.releaseDates[0].dateOfRelease")
                .entity(String.class)
                .satisfies(date ->
                        assertThat(date).matches("\\d{4}-\\d{2}-\\d{2}")
                );
    }

    @Test
    void shouldSerializeLocalDateTime() {
        Movie movie = getSingleMovie();

        graphQlTester
                .document("""
                        query GetMovie($id: ID!) {
                            movie(id: $id) {
                                createdAt
                                updatedAt
                            }
                        }
                        """)
                .variable("id", movie.getId().toString())
                .execute()
                .path("movie.createdAt")
                .entity(String.class)
                .satisfies(createdAt ->
                        assertThat(createdAt).isNotBlank()
                )
                .path("movie.updatedAt")
                .entity(String.class)
                .satisfies(updatedAt ->
                        assertThat(updatedAt).isNotBlank()
                );
    }
}
