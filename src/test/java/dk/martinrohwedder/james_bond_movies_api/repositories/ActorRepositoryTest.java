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

        assertThat(actor.isPresent()).isTrue();
        assertThat(actor.get()).extracting(Actor::getId).isEqualTo(actors.getFirst().getId());
        assertThat(actor.get()).extracting(Actor::getName).isEqualTo(actors.getFirst().getName());
    }
}
