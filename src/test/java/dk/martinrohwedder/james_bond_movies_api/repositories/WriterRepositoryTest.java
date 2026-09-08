package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class WriterRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private WriterRepository writerRepository;

    @Test
    void should_return_writer_by_id() {
        Writer expected = writerRepository.findAllByOrderByNameAsc().getFirst();

        Optional<Writer> result = writerRepository.findById(expected.getId());

        assertThat(result)
                .isPresent()
                .get()
                .satisfies(writer -> {
                    assertThat(writer.getId()).isEqualTo(expected.getId());
                    assertThat(writer.getName()).isEqualTo(expected.getName());
                });
    }

    @Test
    void should_return_empty_optional_when_writer_id_is_not_found() {
        Optional<Writer> result = writerRepository.findById(UUID.randomUUID());

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_writer_by_name() {
        List<Writer> result = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Ian Fleming");

        assertThat(result)
                .singleElement()
                .extracting(Writer::getName)
                .isEqualTo("Ian Fleming");
    }

    @Test
    void should_return_writer_by_name_ignoring_case() {
        List<Writer> result = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("iaN fleMiNG");

        assertThat(result)
                .singleElement()
                .extracting(Writer::getName)
                .isEqualTo("Ian Fleming");
    }

    @Test
    void should_return_empty_list_when_writer_name_is_not_found() {
        List<Writer> result = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Wrong Writer Name");

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_all_writers_ordered_by_name() {
        List<String> names = writerRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Writer::getName)
                .toList();

        assertThat(names)
                .isNotEmpty()
                .isSorted();
    }

    @Test
    void should_return_same_results_for_different_name_casing() {
        List<Writer> lowerCase = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("ian fleming");
        List<Writer> mixedCase = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("iaN fleMiNG");

        assertThat(lowerCase)
                .extracting(Writer::getId)
                .containsExactlyElementsOf(
                        mixedCase.stream().map(Writer::getId).toList()
                );
    }

    @Test
    void should_return_empty_list_when_writer_name_is_blank() {
        List<Writer> result = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("   ");

        assertThat(result).isEmpty();
    }
}
