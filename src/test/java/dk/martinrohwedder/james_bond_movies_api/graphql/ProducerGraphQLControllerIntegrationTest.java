package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import dk.martinrohwedder.james_bond_movies_api.repositories.ProducerRepository;
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
public class ProducerGraphQLControllerIntegrationTest {
    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private GraphQlTester graphQlTester;

    private Producer getSingleProducer() {
        return producerRepository.findAllByOrderByNameAsc()
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private Producer getSingleProducer(String name) {
        return producerRepository.findAllByNameIgnoreCaseOrderByNameAsc(name)
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    // -------------------------------------------------------------------------
    // All Producers
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_producers() {
        graphQlTester
                .document("""
                        query {
                            producers {
                                id
                                name
                                biography
                                nationality
                            }
                        }
                        """)
                .execute()
                .path("producers")
                .entityList(Object.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producers returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("producers[0].id")
                .entity(String.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producer ID")
                                .isNotBlank()
                )
                .path("producers[0].name")
                .entity(String.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producer Name")
                                .isNotBlank()
                )
                .path("producers[0].biography")
                .entity(String.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producer Biography")
                                .isNotBlank()
                )
                .path("producers[0].nationality")
                .entity(String.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producer Nationality")
                                .isNotBlank()
                );
    }

    @Test
    void should_return_producer_with_minimal_movie() {
        graphQlTester
                .document("""
                        query {
                            producers {
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
                .path("producers")
                .entityList(Object.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producers returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("producers[0].id")
                .entity(String.class)
                .satisfies(producers ->
                        assertThat(producers)
                                .as("Producer ID")
                                .isNotBlank()
                )
                .path("producers[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("producers[0].movies[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Movie ID")
                                .isNotBlank()
                )
                .path("producers[0].movies[0].movieNumber")
                .entity(Integer.class)
                .satisfies(movieNumber ->
                        assertThat(movieNumber)
                                .as("Movie Number")
                                .isNotNull()
                )
                .path("producers[0].movies[0].title")
                .entity(String.class)
                .satisfies(title ->
                        assertThat(title)
                                .as("Movie Title")
                                .isNotBlank()
                );
    }

    // -------------------------------------------------------------------------
    // Producer By ID
    // -------------------------------------------------------------------------

    @Test
    void should_return_producer_by_id() {
        Producer producer = getSingleProducer();

        graphQlTester
                .document("""
                        query GetProducerById($id: ID!) {
                            producer(id: $id) {
                                id
                                name
                                biography
                                nationality
                            }
                        }
                """)
                .variable("id", producer.getId())
                .execute()
                .path("producer.id")
                .entity(String.class)
                .isEqualTo(producer.getId().toString())
                .path("producer.name")
                .entity(String.class)
                .isEqualTo(producer.getName())
                .path("producer.biography")
                .entity(String.class)
                .isEqualTo(producer.getBiography())
                .path("producer.nationality")
                .entity(String.class)
                .isEqualTo(producer.getNationality());
    }

    @Test
    void should_return_producer_by_id_with_minimal_movie() {
        Producer producer = getSingleProducer();

        graphQlTester
                .document("""
                        query GetProducerById($id: ID!) {
                            producer(id: $id) {
                                id
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                """)
                .variable("id", producer.getId())
                .execute()
                .path("producer.id")
                .entity(String.class)
                .isEqualTo(producer.getId().toString())
                .path("producer.movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("producer.movies[0].id")
                .entity(String.class)
                .isEqualTo(producer.getMovies().getFirst().getId().toString())
                .path("producer.movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(producer.getMovies().getFirst().getMovieNumber())
                .path("producer.movies[0].title")
                .entity(String.class)
                .isEqualTo(producer.getMovies().getFirst().getTitle());
    }

    @Test
    void should_return_null_when_producer_id_does_not_exist() {
        UUID producerId = UUID.randomUUID();

        graphQlTester
                .document("""
                        query GetProducerById($id: ID!) {
                            producer(id: $id) {
                                id
                                name
                            }
                        }
                """)
                .variable("id", producerId.toString())
                .execute()
                .errors()
                .verify()
                .path("producer")
                .valueIsNull();
    }

    // -------------------------------------------------------------------------
    // Producer By Name
    // -------------------------------------------------------------------------

    @Test
    void should_return_producer_by_name() {
        Producer producer = getSingleProducer("Albert R. Broccoli");

        graphQlTester
                .document("""
                        query GetProducerByName($name: String!) {
                            producerByName(name: $name) {
                                id
                                name
                                biography
                                nationality
                            }
                        }
                """)
                .variable("name", producer.getName())
                .execute()
                .path("producerByName[0].id")
                .entity(String.class)
                .isEqualTo(producer.getId().toString())
                .path("producerByName[0].name")
                .entity(String.class)
                .isEqualTo(producer.getName())
                .path("producerByName[0].biography")
                .entity(String.class)
                .isEqualTo(producer.getBiography())
                .path("producerByName[0].nationality")
                .entity(String.class)
                .isEqualTo(producer.getNationality());
    }

    @Test
    void should_return_director_by_name_with_minimal_movie() {
        Producer producer = getSingleProducer("Albert R. Broccoli");

        graphQlTester
                .document("""
                        query GetProducerByName($name: String!) {
                            producerByName(name: $name) {
                                name
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                """)
                .variable("name", producer.getName())
                .execute()
                .path("producerByName[0].name")
                .entity(String.class)
                .isEqualTo(producer.getName())
                .path("producerByName[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("producerByName[0].movies[0].id")
                .entity(String.class)
                .isEqualTo(producer.getMovies().getFirst().getId().toString())
                .path("producerByName[0].movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(producer.getMovies().getFirst().getMovieNumber())
                .path("producerByName[0].movies[0].title")
                .entity(String.class)
                .isEqualTo(producer.getMovies().getFirst().getTitle());
    }

    @Test
    void should_return_empty_list_when_producer_name_does_not_exist() {
        String wrongName = "Wrong name";

        graphQlTester
                .document("""
                        query GetProducerByName($name: String!) {
                            producerByName(name: $name) {
                                id
                                name
                            }
                        }
                """)
                .variable("name", wrongName)
                .execute()
                .path("producerByName")
                .entityList(Object.class)
                .hasSize(0);
    }
}
