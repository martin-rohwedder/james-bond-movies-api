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
class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void should_find_actor_by_id() {
        Actor expected = actorRepository.findAllByOrderByNameAsc().getFirst();

        Optional<Actor> result = actorRepository.findById(expected.getId());

        assertThat(result)
                .isPresent()
                .get()
                .satisfies(actor -> {
                    assertThat(actor.getId()).isEqualTo(expected.getId());
                    assertThat(actor.getName()).isEqualTo(expected.getName());
                });
    }

    @Test
    void should_return_empty_optional_when_actor_id_is_not_found() {
        Optional<Actor> result = actorRepository.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
    }

    @Test
    void should_find_actors_by_name() {
        List<Actor> result =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Joe Don Baker");

        assertThat(result)
                .hasSize(2)
                .extracting(Actor::getName)
                .containsOnly("Joe Don Baker");
    }

    @Test
    void should_find_actor_by_name_ignoring_case() {
        List<Actor> result =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("sean coNNerY");

        assertThat(result)
                .singleElement()
                .extracting(Actor::getName)
                .isEqualTo("Sean Connery");
    }

    @Test
    void should_find_actor_by_name_with_special_characters() {
        List<Actor> result =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Gert Fröbe");

        assertThat(result)
                .singleElement()
                .extracting(Actor::getName)
                .isEqualTo("Gert Fröbe");
    }

    @Test
    void should_find_actor_by_name_case_insensitive_with_special_character() {
        List<Actor> result =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("gert fröbe");

        assertThat(result)
                .singleElement()
                .extracting(Actor::getName)
                .isEqualTo("Gert Fröbe");
    }

    @Test
    void should_return_empty_list_when_actor_name_is_not_found() {
        List<Actor> result =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Wrong Actor Name");

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_all_actors_ordered_by_name() {
        List<String> names = actorRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Actor::getName)
                .toList();

        assertThat(names)
                .isNotEmpty()
                .startsWith(
                        "Adolfo Celi",
                        "Akiko Wakabayashi"
                );
    }

    @Test
    void should_return_same_results_for_different_name_casing() {
        List<Actor> lowerCase =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("sean connery");

        List<Actor> mixedCase =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("SeAn CoNnErY");

        assertThat(lowerCase)
                .extracting(Actor::getId)
                .containsExactlyElementsOf(
                        mixedCase.stream().map(Actor::getId).toList()
                );
    }

    @Test
    void should_return_empty_list_when_actor_name_is_blank() {
        List<Actor> result =
                actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("   ");

        assertThat(result).isEmpty();
    }
}