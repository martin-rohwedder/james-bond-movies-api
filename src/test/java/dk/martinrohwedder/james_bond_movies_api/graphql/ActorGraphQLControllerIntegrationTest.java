package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
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
public class ActorGraphQLControllerIntegrationTest {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GraphQlTester graphQlTester;

    private Actor getSingleActor() {
        return actorRepository.findAllByOrderByNameAsc()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Actor not found")
                );
    }

    private Actor getSingleActor(String name) {
        return actorRepository.findAllByNameIgnoreCaseOrderByNameAsc(name)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Actor not found")
                );
    }

    // -------------------------------------------------------------------------
    // All Actors
    // -------------------------------------------------------------------------

    @Test
    void shouldReturnAllActors() {
        graphQlTester
                .document("""
                        query {
                            actors {
                                id
                                name
                                characterRole
                                biography
                                nationality
                            }
                        }
                        """)
                .execute()
                .path("actors")
                .entityList(Object.class)
                .satisfies(actors ->
                        assertThat(actors)
                                .as("Actors returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("actors[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Actor ID")
                                .isNotBlank()
                )
                .path("actors[0].name")
                .entity(String.class)
                .satisfies(name ->
                        assertThat(name)
                                .as("Actor Name")
                                .isNotBlank()
                )
                .path("actors[0].characterRole")
                .entity(String.class)
                .satisfies(characterRole ->
                        assertThat(characterRole)
                                .as("Actor Character Role")
                                .isNotBlank()
                )
                .path("actors[0].biography")
                .entity(String.class)
                .satisfies(biography ->
                        assertThat(biography)
                                .as("Actor Biography")
                                .isNotBlank()
                )
                .path("actors[0].nationality")
                .entity(String.class)
                .satisfies(nationality ->
                        assertThat(nationality)
                                .as("Actor Nationality")
                                .isNotBlank()
                );
    }

    @Test
    void shouldReturnActorsWithMinimalMovie() {
        graphQlTester
                .document("""
                        query {
                            actors {
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
                .path("actors")
                .entityList(Object.class)
                .satisfies(actors ->
                        assertThat(actors)
                                .as("Actors returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("actors[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Actor ID")
                                .isNotBlank()
                )
                .path("actors[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("actors[0].movies[0].id")
                .entity(String.class)
                .satisfies(id ->
                        assertThat(id)
                                .as("Movie ID")
                                .isNotBlank()
                )
                .path("actors[0].movies[0].movieNumber")
                .entity(Integer.class)
                .satisfies(movieNumber ->
                        assertThat(movieNumber)
                                .as("Movie Number")
                                .isNotNull()
                )
                .path("actors[0].movies[0].title")
                .entity(String.class)
                .satisfies(title ->
                        assertThat(title)
                                .as("Movie Title")
                                .isNotBlank()
                );
    }

    // -------------------------------------------------------------------------
    // Actor By ID
    // -------------------------------------------------------------------------

    @Test
    void shouldReturnActorById() {
        Actor actor = getSingleActor();

        graphQlTester
                .document("""
                        query GetActorById($id: ID!) {
                            actor(id: $id) {
                                id
                                name
                                characterRole
                                biography
                                nationality
                            }
                        }
                        """)
                .variable("id", actor.getId().toString())
                .execute()
                .path("actor.id")
                .entity(String.class)
                .isEqualTo(actor.getId().toString())
                .path("actor.name")
                .entity(String.class)
                .isEqualTo(actor.getName())
                .path("actor.characterRole")
                .entity(String.class)
                .isEqualTo(actor.getCharacterRole())
                .path("actor.biography")
                .entity(String.class)
                .isEqualTo(actor.getBiography())
                .path("actor.nationality")
                .entity(String.class)
                .isEqualTo(actor.getNationality());
    }

    @Test
    void shouldReturnActorByIdWithMinimalMovie() {
        Actor actor = getSingleActor();

        graphQlTester
                .document("""
                        query GetActorById($id: ID!) {
                            actor(id: $id) {
                                id
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                        """)
                .variable("id", actor.getId().toString())
                .execute()
                .path("actor.id")
                .entity(String.class)
                .isEqualTo(actor.getId().toString())
                .path("actor.movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("actor.movies[0].id")
                .entity(String.class)
                .isEqualTo(actor.getMovies().getFirst().getId().toString())
                .path("actor.movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(actor.getMovies().getFirst().getMovieNumber())
                .path("actor.movies[0].title")
                .entity(String.class)
                .isEqualTo(actor.getMovies().getFirst().getTitle());
    }

    @Test
    void shouldReturnNullWhenActorIdDoesNotExist() {
        UUID actorId = UUID.randomUUID();

        graphQlTester
                .document("""
                        query GetActorById($id: ID!) {
                            actor(id: $id) {
                                id
                                name
                            }
                        }
                        """)
                .variable("id", actorId.toString())
                .execute()
                .errors()
                .verify()
                .path("actor")
                .valueIsNull();
    }

    // -------------------------------------------------------------------------
    // Actor By Name
    // -------------------------------------------------------------------------

    @Test
    void shouldReturnActorByName() {
        Actor actor = getSingleActor("Sean Connery");

        graphQlTester
                .document("""
                        query GetActorByName($name: String!) {
                            actorByName(name: $name) {
                                id
                                name
                                characterRole
                                biography
                                nationality
                            }
                        }
                        """)
                .variable("name", actor.getName())
                .execute()
                .path("actorByName[0].id")
                .entity(String.class)
                .isEqualTo(actor.getId().toString())
                .path("actorByName[0].name")
                .entity(String.class)
                .isEqualTo(actor.getName())
                .path("actorByName[0].characterRole")
                .entity(String.class)
                .isEqualTo(actor.getCharacterRole())
                .path("actorByName[0].biography")
                .entity(String.class)
                .isEqualTo(actor.getBiography())
                .path("actorByName[0].nationality")
                .entity(String.class)
                .isEqualTo(actor.getNationality());
    }

    @Test
    void shouldReturnActorByNameWithMinimalMovie() {
        Actor actor = getSingleActor("Sean Connery");

        graphQlTester
                .document("""
                        query GetActorByName($name: String!) {
                            actorByName(name: $name) {
                                name
                                movies {
                                    id
                                    movieNumber
                                    title
                                }
                            }
                        }
                        """)
                .variable("name", actor.getName())
                .execute()
                .path("actorByName[0].name")
                .entity(String.class)
                .isEqualTo(actor.getName())
                .path("actorByName[0].movies")
                .entityList(Object.class)
                .hasSizeGreaterThan(0)
                .path("actorByName[0].movies[0].id")
                .entity(String.class)
                .isEqualTo(actor.getMovies().getFirst().getId().toString())
                .path("actorByName[0].movies[0].movieNumber")
                .entity(Integer.class)
                .isEqualTo(actor.getMovies().getFirst().getMovieNumber())
                .path("actorByName[0].movies[0].title")
                .entity(String.class)
                .isEqualTo(actor.getMovies().getFirst().getTitle());
    }
}
