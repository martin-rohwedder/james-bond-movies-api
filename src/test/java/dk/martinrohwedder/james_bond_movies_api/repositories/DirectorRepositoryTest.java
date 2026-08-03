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
                .extracting(Director::getId, Director::getName)
                .containsExactly(expected.getId(), expected.getName());
    }

    @Test
    void should_return_empty_optional_when_director_id_is_not_found() {
        var result = directorRepository.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
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

    @Test
    void should_return_director_by_name() {
        List<Director> result = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Guy Hamilton");

        assertThat(result)
                .singleElement()
                .extracting(Director::getName)
                .isEqualTo("Guy Hamilton");
    }

    @Test
    void should_find_director_by_name_ignoring_case() {
        List<Director> result = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("gUy hAMilTon");

        assertThat(result)
                .singleElement()
                .extracting(Director::getName)
                .isEqualTo("Guy Hamilton");
    }

    @Test
    void should_return_same_results_for_different_name_casing() {
        List<Director> lower = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("guy hamilton");
        List<Director> mixed = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("GuY HaMilTon");

        assertThat(lower)
                .extracting(Director::getId)
                .containsExactlyElementsOf(
                        mixed.stream().map(Director::getId).toList()
                );
    }

    @Test
    void should_return_empty_list_when_director_name_is_not_found() {
        List<Director> result = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Unknown Director");

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_empty_list_when_director_name_is_blank() {
        List<Director> result = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("   ");

        assertThat(result).isEmpty();
    }
}