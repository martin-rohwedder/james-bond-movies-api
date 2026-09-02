package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MusicRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private MusicRepository musicRepository;

    @Test
    void should_return_music_by_performer() {
        List<Music> result = musicRepository.findAllByPerformerIgnoreCase("Shirley Bassey");

        assertThat(result)
                .extracting(Music::getPerformer)
                .containsExactlyElementsOf(
                        result.stream().map(Music::getPerformer).toList()
                );
    }

    @Test
    void should_return_music_by_performer_ignoring_case() {
        List<Music> result = musicRepository.findAllByPerformerIgnoreCase("ShIrLey BASSey");

        assertThat(result)
                .extracting(Music::getPerformer)
                .containsExactlyElementsOf(
                        result.stream().map(Music::getPerformer).toList()
                );
    }

    @Test
    void should_return_same_results_for_different_performer_name_casing() {
        List<Music> lower = musicRepository.findAllByPerformerIgnoreCase("shirley bassey");
        List<Music> mixed = musicRepository.findAllByPerformerIgnoreCase("ShIrLey BASSey");

        assertThat(lower)
                .extracting(Music::getId)
                .containsExactlyElementsOf(
                        mixed.stream().map(Music::getId).toList()
                );
    }

    @Test
    void should_return_empty_list_when_performer_name_is_not_found() {
        List<Music> result = musicRepository.findAllByPerformerIgnoreCase("unknown performer");

        assertThat(result).isEmpty();
    }

    @Test
    void should_return_empty_list_when_performer_name_is_blank() {
        List<Music> result = musicRepository.findAllByPerformerIgnoreCase("  ");

        assertThat(result).isEmpty();
    }
}
