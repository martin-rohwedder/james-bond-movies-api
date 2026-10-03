package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import dk.martinrohwedder.james_bond_movies_api.repositories.DirectorRepository;
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
public class DirectorGraphQLControllerIntegrationTest {
    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GraphQlTester graphQlTester;

    private Director getSingleDirector() {
        return directorRepository.findAllByOrderByNameAsc()
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private Director getSingleDirector(String name) {
        return directorRepository.findAllByNameIgnoreCaseOrderByNameAsc(name)
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    // -------------------------------------------------------------------------
    // All Directors
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_directors() {
        graphQlTester
                .document("""
                        query {
                            directors {
                                id
                                name
                                biography
                                nationality
                            }
                        }
                        """)
                .execute()
                .path("directors")
                .entityList(Object.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Directors returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("directors[0].id")
                .entity(String.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Director ID")
                                .isNotBlank()
                )
                .path("directors[0].name")
                .entity(String.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Director Name")
                                .isNotBlank()
                )
                .path("directors[0].biography")
                .entity(String.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Director Biography")
                                .isNotBlank()
                )
                .path("directors[0].nationality")
                .entity(String.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Director Nationality")
                                .isNotBlank()
                );
    }

    @Test
    void should_return_director_with_minimal_movie() {
        graphQlTester
                .document("""
                        query {
                            directors {
                                id
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                        """)
                .execute()
                .path("directors")
                .entityList(Object.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Directors returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("directors[0].id")
                .entity(String.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Director ID")
                                .isNotBlank()
                )
                .path("directors[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("directors[0].movies[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Movie ID")
                                .isNotBlank()
                )
                .path("directors[0].movies[0].movieNumber")
                .entity(Integer.class)
                .satisfies(movieNumber ->
                        assertThat(movieNumber)
                                .as("Movie Number")
                                .isNotNull()
                )
                .path("directors[0].movies[0].title")
                .entity(String.class)
                .satisfies(title ->
                        assertThat(title)
                                .as("Movie Title")
                                .isNotBlank()
                );
    }

    // -------------------------------------------------------------------------
    // Director By ID
    // -------------------------------------------------------------------------

    @Test
    void should_return_director_by_id() {
        Director director = getSingleDirector();

        graphQlTester
                .document("""
                        query GetDirectorById($id: ID!) {
                            director(id: $id) {
                                id
                                name
                                biography
                                nationality
                            }
                        }
                """)
                .variable("id", director.getId())
                .execute()
                .path("director.id")
                .entity(String.class)
                .isEqualTo(director.getId().toString())
                .path("director.name")
                .entity(String.class)
                .isEqualTo(director.getName())
                .path("director.biography")
                .entity(String.class)
                .isEqualTo(director.getBiography())
                .path("director.nationality")
                .entity(String.class)
                .isEqualTo(director.getNationality());
    }

    @Test
    void should_return_director_by_id_with_minimal_movie() {
        Director director = getSingleDirector();

        graphQlTester
                .document("""
                        query GetDirectorById($id: ID!) {
                            director(id: $id) {
                                id
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                """)
                .variable("id", director.getId())
                .execute()
                .path("director.id")
                .entity(String.class)
                .isEqualTo(director.getId().toString())
                .path("director.movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("director.movies[0].id")
                .entity(String.class)
                .isEqualTo(director.getMovies().getFirst().getId().toString())
                .path("director.movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(director.getMovies().getFirst().getMovieNumber())
                .path("director.movies[0].title")
                .entity(String.class)
                .isEqualTo(director.getMovies().getFirst().getTitle());
    }

    @Test
    void should_return_null_when_director_id_does_not_exist() {
        UUID directorId = UUID.randomUUID();

        graphQlTester
                .document("""
                        query GetDirectorById($id: ID!) {
                            director(id: $id) {
                                id
                                name
                            }
                        }
                """)
                .variable("id", directorId.toString())
                .execute()
                .errors()
                .verify()
                .path("director")
                .valueIsNull();
    }

    // -------------------------------------------------------------------------
    // Director By Name
    // -------------------------------------------------------------------------

    @Test
    void should_return_director_by_name() {
        Director director = getSingleDirector("Guy Hamilton");

        graphQlTester
                .document("""
                        query GetDirectorByName($name: String!) {
                            directorByName(name: $name) {
                                id
                                name
                                biography
                                nationality
                            }
                        }
                """)
                .variable("name", director.getName())
                .execute()
                .path("directorByName[0].id")
                .entity(String.class)
                .isEqualTo(director.getId().toString())
                .path("directorByName[0].name")
                .entity(String.class)
                .isEqualTo(director.getName())
                .path("directorByName[0].biography")
                .entity(String.class)
                .isEqualTo(director.getBiography())
                .path("directorByName[0].nationality")
                .entity(String.class)
                .isEqualTo(director.getNationality());
    }

    @Test
    void should_return_director_by_name_with_minimal_movie() {
        Director director = getSingleDirector("Guy Hamilton");

        graphQlTester
                .document("""
                        query GetDirectorByName($name: String!) {
                            directorByName(name: $name) {
                                name
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                """)
                .variable("name", director.getName())
                .execute()
                .path("directorByName[0].name")
                .entity(String.class)
                .isEqualTo(director.getName())
                .path("directorByName[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("directorByName[0].movies[0].id")
                .entity(String.class)
                .isEqualTo(director.getMovies().getFirst().getId().toString())
                .path("directorByName[0].movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(director.getMovies().getFirst().getMovieNumber())
                .path("directorByName[0].movies[0].title")
                .entity(String.class)
                .isEqualTo(director.getMovies().getFirst().getTitle());
    }
}
