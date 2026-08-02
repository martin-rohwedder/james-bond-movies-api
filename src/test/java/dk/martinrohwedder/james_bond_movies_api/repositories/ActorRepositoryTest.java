package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@Testcontainers
public class ActorRepositoryTest {
    @Autowired
    private ActorRepository actorRepository;

    @Test
    void should_find_a_specific_actor_by_id() {
        List<Actor> actors = actorRepository.findAll();
        Optional<Actor> actor = actorRepository.findById(actors.getFirst().getId());

        assertThat(actor)
                .isPresent()
                .get()
                .extracting(Actor::getName)
                .isEqualTo(actors.getFirst().getName());
    }

    @Test
    void should_return_empty_optional_when_actor_id_is_not_found() {
        Optional<Actor> actor = actorRepository.findById(UUID.randomUUID());

        assertThat(actor).isEmpty();
    }

    @Test
    void should_find_a_specific_actor_by_name() {
        List<Actor> actors = actorRepository.findAllByNameIgnoreCase("Joe Don Baker");

        assertThat(actors)
                .hasSize(2)
                .allMatch(actor -> actor.getName().equals("Joe Don Baker"));
    }

    @Test
    void should_find_a_specific_actor_by_name_ignoring_case() {
        List<Actor> actors = actorRepository.findAllByNameIgnoreCase("sean coNNerY");

        assertThat(actors).size().isEqualTo(1);
        assertThat(actors.getFirst()).extracting(Actor::getName).isEqualTo("Sean Connery");
    }

    @Test
    void should_find_all_actors() {
        List<Actor> actors = actorRepository.findAll();

        assertThat(actors.size()).isGreaterThan(0);
    }

    @Test
    void should_return_empty_list_when_actor_name_is_not_found() {
        List<Actor> actors = actorRepository.findAllByNameIgnoreCase("Wrong Actor Name");

        assertThat(actors).isEmpty();
    }

    @Test
    void should_find_actor_by_name_with_special_character() {
        List<Actor> actors = actorRepository.findAllByNameIgnoreCase("Gert Fröbe");

        assertThat(actors)
                .hasSize(1)
                .first()
                .extracting(Actor::getName)
                .isEqualTo("Gert Fröbe");
    }
}
