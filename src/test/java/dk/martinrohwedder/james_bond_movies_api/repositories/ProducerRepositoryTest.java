package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProducerRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    void should_find_producer_by_id() {
        Producer expected = producerRepository.findAllByOrderByNameAsc().getFirst();

        var result = producerRepository.findById(expected.getId());

        assertThat(result)
                .isPresent()
                .get()
                .extracting(Producer::getId, Producer::getName)
                .containsExactly(expected.getId(), expected.getName());
    }

    @Test
    void should_return_empty_optional_when_producer_id_is_not_found() {
        var result = producerRepository.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_all_producers_ordered_by_name() {
        List<String> names = producerRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Producer::getName)
                .toList();

        List<String> sorted = names.stream()
                .sorted()
                .toList();

        assertThat(names)
                .isNotEmpty()
                .isEqualTo(sorted);
    }

    @Test
    void should_return_producer_by_name() {
        List<Producer> result = producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Albert R. Broccoli");

        assertThat(result)
                .singleElement()
                .extracting(Producer::getName)
                .isEqualTo("Albert R. Broccoli");
    }

    @Test
    void should_find_producer_by_name_ignoring_case() {
        List<Producer> result = producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("aLBeRt r. bROcCoLi");

        assertThat(result)
                .singleElement()
                .extracting(Producer::getName)
                .isEqualTo("Albert R. Broccoli");
    }

    @Test
    void should_return_same_results_for_different_name_casing() {
        List<Producer> lower = producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("albert r. broccoli");
        List<Producer> mixed = producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("AlBeRt R. BrOcCoLi");

        assertThat(lower)
                .extracting(Producer::getId)
                .containsExactlyElementsOf(
                        mixed.stream().map(Producer::getId).toList()
                );
    }

    @Test
    void should_return_empty_list_when_producer_name_is_not_found() {
        List<Producer> result = producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Unknown Producer");

        assertThat(result).isEmpty();
    }
}