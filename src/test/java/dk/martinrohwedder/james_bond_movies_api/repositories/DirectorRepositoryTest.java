package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@Testcontainers
class DirectorRepositoryTest {

    @Autowired
    private DirectorRepository directorRepository;

    @Test
    void should_find_director_by_id() {
        Director expected = directorRepository.findAllByOrderByNameAsc().getFirst();

        var result = directorRepository.findById(expected.getId());

        assertThat(result)
                .isPresent()
                .get()
                .satisfies(director -> {
                    assertThat(director.getId()).isEqualTo(expected.getId());
                    assertThat(director.getName()).isEqualTo(expected.getName());
                });
    }

    @Test
    void should_return_empty_optional_when_director_id_is_not_found() {
        var result = directorRepository.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_all_directors() {
        List<Director> result = directorRepository.findAllByOrderByNameAsc();

        assertThat(result).isNotEmpty();
    }

    @Test
    void should_return_all_directors_ordered_by_name() {
        List<String> names = directorRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Director::getName)
                .toList();

        assertThat(names)
                .isNotEmpty()
                .startsWith(
                        "Cary Joji Fukunaga",
                        "Guy Hamilton"
                );
    }
}