package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.dtos.ActorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.dtos.GenreResponseDto;
import dk.martinrohwedder.james_bond_movies_api.dtos.ReleaseDateResponseDto;
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

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@Testcontainers
@SpringBootTest
@AutoConfigureGraphQlTester
@ActiveProfiles("test")
class MovieGraphQLControllerIntegrationTest {
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
    void shouldReturnAllMovies() {
        graphQlTester
                .document("""
                        query {
                            movies {
                                id
                                movieNumber
                                title
                            }
                        }
                        """)
                .execute()
                .path("movies")
                .entityList(Object.class)
                .satisfies(movies ->
                    assertThat(movies)
                            .as("movies returned by GraphQL API")
                            .isNotEmpty()
                )
                .path("movies[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Movie ID")
                                .isNotBlank()
                )
                .path("movies[0].movieNumber")
                .entity(Integer.class)
                .satisfies(number ->
                        assertThat(number)
                                .as("Movie Number")
                                .isNotNull()
                )
                .path("movies[0].title")
                .entity(String.class)
                .satisfies(title ->
                        assertThat(title)
                                .as("Movie Title")
                                .isNotBlank()
                );
    }

    @Test
    void shouldReturnMovieById() {
        Movie movie = getSingleMovie();

        graphQlTester
                .document("""
                        query GetMovie($id: ID!) {
                            movie(id: $id) {
                                id
                                movieNumber
                                title
                            }
                        }
                        """)
                .variable("id", movie.getId().toString())
                .execute()
                .path("movie.id")
                .entity(String.class)
                .isEqualTo(movie.getId().toString())
                .path("movie.movieNumber")
                .entity(Integer.class)
                .isEqualTo(movie.getMovieNumber())
                .path("movie.title")
                .entity(String.class)
                .isEqualTo(movie.getTitle());
    }

    @Test
    void shouldReturnNullWhenMovieDoesNotExist() {
        UUID movieId = UUID.randomUUID();

        graphQlTester
                .document("""
                        query GetMovie($id: ID!) {
                            movie(id: $id) {
                                id
                                title
                            }
                        }
                        """)
                .variable("id", movieId.toString())
                .execute()
                .errors()
                .verify()
                .path("movie")
                .valueIsNull();
    }

    @Test
    void shouldReturnNestedMovieData() {
        Movie movie = getSingleMovie();

        graphQlTester
                .document("""
                        query GetMovie($id: ID!) {
                            movie(id: $id) {
                                id
                                movieNumber
                                title

                                director {
                                    id
                                    name
                                    nationality
                                    dateOfBirth
                                    dateOfDeath
                                }

                                actors {
                                    id
                                    name
                                    characterRole
                                }

                                genres {
                                    title
                                }

                                releaseDates {
                                    dateOfRelease
                                    country
                                    countryCode
                                }

                                boxOffice {
                                    budgetUsd
                                    grossRevenueUsAndCanadaUsd
                                    grossRevenueWorldwideUsd
                                }
                            }
                        }
                        """)
                .variable("id", movie.getId().toString())
                .execute()
                .path("movie.id")
                .entity(String.class)
                .isEqualTo(movie.getId().toString())
                .path("movie.movieNumber")
                .entity(Integer.class)
                .isEqualTo(movie.getMovieNumber())
                .path("movie.title")
                .entity(String.class)
                .isEqualTo(movie.getTitle())
                .path("movie.director.name")
                .entity(String.class)
                .isEqualTo(movie.getDirector().getName())
                .path("movie.actors")
                .entityList(ActorResponseDto.class)
                .hasSizeGreaterThan(0)
                .path("movie.actors[0].name")
                .entity(String.class)
                .isEqualTo(movie.getActors().getFirst().getName())
                .path("movie.genres")
                .entityList(GenreResponseDto.class)
                .hasSizeGreaterThan(0)
                .path("movie.genres[0].title")
                .entity(String.class)
                .isEqualTo(movie.getGenres().getFirst().getTitle())
                .path("movie.releaseDates")
                .entityList(ReleaseDateResponseDto.class)
                .hasSizeGreaterThan(0)
                .path("movie.releaseDates[0].dateOfRelease")
                .entity(String.class)
                .isEqualTo(movie.getReleaseDates().getFirst().getDateOfRelease().toString());
    }
}
