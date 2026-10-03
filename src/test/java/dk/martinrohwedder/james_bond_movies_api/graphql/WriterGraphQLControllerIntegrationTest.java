package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import dk.martinrohwedder.james_bond_movies_api.repositories.WriterRepository;
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
public class WriterGraphQLControllerIntegrationTest {
    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private GraphQlTester graphQlTester;

    private Writer getSingleWriter() {
        return writerRepository.findAllByOrderByNameAsc()
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private Writer getSingleWriter(String name) {
        return writerRepository.findAllByNameIgnoreCaseOrderByNameAsc(name)
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    // -------------------------------------------------------------------------
    // All Writers
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_writers() {
        graphQlTester
                .document("""
                        query {
                            writers {
                                id
                                name
                            }
                        }
                        """)
                .execute()
                .path("writers")
                .entityList(Object.class)
                .satisfies(writers ->
                        assertThat(writers)
                                .as("Writers returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("writers[0].id")
                .entity(String.class)
                .satisfies(writers ->
                        assertThat(writers)
                                .as("Writer ID")
                                .isNotBlank()
                )
                .path("writers[0].name")
                .entity(String.class)
                .satisfies(writers ->
                        assertThat(writers)
                                .as("Writer Name")
                                .isNotBlank()
                );
    }

    @Test
    void should_return_writers_with_minimal_movie() {
        graphQlTester
                .document("""
                        query {
                            writers {
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
                .path("writers")
                .entityList(Object.class)
                .satisfies(directors ->
                        assertThat(directors)
                                .as("Writers returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("writers[0].id")
                .entity(String.class)
                .satisfies(writers ->
                        assertThat(writers)
                                .as("Writer ID")
                                .isNotBlank()
                )
                .path("writers[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("writers[0].movies[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Movie ID")
                                .isNotBlank()
                )
                .path("writers[0].movies[0].movieNumber")
                .entity(Integer.class)
                .satisfies(movieNumber ->
                        assertThat(movieNumber)
                                .as("Movie Number")
                                .isNotNull()
                )
                .path("writers[0].movies[0].title")
                .entity(String.class)
                .satisfies(title ->
                        assertThat(title)
                                .as("Movie Title")
                                .isNotBlank()
                );
    }

    // -------------------------------------------------------------------------
    // Writer By ID
    // -------------------------------------------------------------------------

    @Test
    void should_return_writer_by_id() {
        Writer writer = getSingleWriter();

        graphQlTester
                .document("""
                        query GetWriterById($id: ID!) {
                            writer(id: $id) {
                                id
                                name
                            }
                        }
                """)
                .variable("id", writer.getId())
                .execute()
                .path("writer.id")
                .entity(String.class)
                .isEqualTo(writer.getId().toString())
                .path("writer.name")
                .entity(String.class)
                .isEqualTo(writer.getName());
    }

    @Test
    void should_return_writer_by_id_with_minimal_movie() {
        Writer writer = getSingleWriter();

        graphQlTester
                .document("""
                        query GetWriterById($id: ID!) {
                            writer(id: $id) {
                                id
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                """)
                .variable("id", writer.getId())
                .execute()
                .path("writer.id")
                .entity(String.class)
                .isEqualTo(writer.getId().toString())
                .path("writer.movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("writer.movies[0].id")
                .entity(String.class)
                .isEqualTo(writer.getMovies().getFirst().getId().toString())
                .path("writer.movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(writer.getMovies().getFirst().getMovieNumber())
                .path("writer.movies[0].title")
                .entity(String.class)
                .isEqualTo(writer.getMovies().getFirst().getTitle());
    }

    @Test
    void should_return_null_when_writer_id_does_not_exist() {
        UUID writerId = UUID.randomUUID();

        graphQlTester
                .document("""
                        query GetWriterById($id: ID!) {
                            writer(id: $id) {
                                id
                                name
                            }
                        }
                """)
                .variable("id", writerId.toString())
                .execute()
                .errors()
                .verify()
                .path("writer")
                .valueIsNull();
    }

    // -------------------------------------------------------------------------
    // Writer By Name
    // -------------------------------------------------------------------------

    @Test
    void should_return_writer_by_name() {
        Writer writer = getSingleWriter("Richard Maibaum");

        graphQlTester
                .document("""
                        query GetWriterByName($name: String!) {
                            writerByName(name: $name) {
                                id
                                name
                            }
                        }
                """)
                .variable("name", writer.getName())
                .execute()
                .path("writerByName[0].id")
                .entity(String.class)
                .isEqualTo(writer.getId().toString())
                .path("writerByName[0].name")
                .entity(String.class)
                .isEqualTo(writer.getName());
    }

    @Test
    void should_return_writer_by_name_with_minimal_movie() {
        Writer writer = getSingleWriter("Richard Maibaum");

        graphQlTester
                .document("""
                        query GetWriterByName($name: String!) {
                            writerByName(name: $name) {
                                name
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                """)
                .variable("name", writer.getName())
                .execute()
                .path("writerByName[0].name")
                .entity(String.class)
                .isEqualTo(writer.getName())
                .path("writerByName[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("writerByName[0].movies[0].id")
                .entity(String.class)
                .isEqualTo(writer.getMovies().getFirst().getId().toString())
                .path("writerByName[0].movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(writer.getMovies().getFirst().getMovieNumber())
                .path("writerByName[0].movies[0].title")
                .entity(String.class)
                .isEqualTo(writer.getMovies().getFirst().getTitle());
    }
}
