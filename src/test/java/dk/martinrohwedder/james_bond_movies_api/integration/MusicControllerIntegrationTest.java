package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import dk.martinrohwedder.james_bond_movies_api.repositories.MusicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MusicControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private MusicRepository musicRepository;

    @Override
    protected String baseUrl() {
        return "/api/music";
    }

    // -------------------------------------------------------------------------
    // GET /api/music
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_music() throws Exception {
        List<Music> musicList = musicRepository.findAll();
        Music music = musicList.getFirst();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(music.getId().toString()))
                .andExpect(jsonPath("$.[0].title").value(music.getTitle()))
                .andExpect(jsonPath("$[0].performer").value(music.getPerformer()))
                .andExpect(jsonPath("$[0].song_url").value(music.getSongUrl()))
                .andExpect(jsonPath("$.length()").value(musicList.size()));
    }

    // -------------------------------------------------------------------------
    // GET /api/music?performer=...
    // -------------------------------------------------------------------------

    @Test
    void should_return_music_filtered_by_performer() throws Exception {
        String performer = "Shirley Bassey";
        List<Music> expected = musicRepository.findAllByPerformerIgnoreCase(performer);

        getByPerformer(performer)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expected.size()))
                .andExpect(jsonPath("$.[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$.[0].title").value(expected.getFirst().getTitle()))
                .andExpect(jsonPath("$.[0].performer").value(expected.getFirst().getPerformer()))
                .andExpect(jsonPath("$.[0].song_url").value(expected.getFirst().getSongUrl()));
    }

    @Test
    void should_return_music_filtered_by_performer_case_insensitive() throws Exception {
        getByPerformer("ShIrLey BaSSey")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].performer").value("Shirley Bassey"));
    }

    @Test
    void should_return_empty_list_when_performer_name_is_not_found() throws Exception {
        getByPerformer("Unknown Performer")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_music_when_performer_name_is_blank() throws Exception {
        getByPerformer("")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(musicRepository.count()));
    }
}
