package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import dk.martinrohwedder.james_bond_movies_api.repositories.MusicRepository;
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
public class MusicGraphQLControllerIntegrationTest {
    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private GraphQlTester graphQlTester;

    private Music getSingleMusic() {
        return musicRepository.findAllByOrderByPerformerAsc()
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    private Music getSingleMusic(String performer) {
        return musicRepository.findAllByPerformerIgnoreCaseOrderByPerformerAsc(performer)
                .stream()
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    // -------------------------------------------------------------------------
    // All Music
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_music() {
        graphQlTester
                .document("""
                        query {
                            music {
                                id
                                title
                                performer
                                songUrl
                            }
                        }
                        """)
                .execute()
                .path("music")
                .entityList(Object.class)
                .satisfies(music ->
                        assertThat(music)
                                .as("music returned by GraphQL API")
                                .isNotEmpty()
                )
                .path("music[0].id")
                .entity(String.class)
                .satisfies(music ->
                        assertThat(music)
                                .as("Music ID")
                                .isNotBlank()
                )
                .path("music[0].title")
                .entity(String.class)
                .satisfies(music ->
                        assertThat(music)
                                .as("Music title")
                                .isNotBlank()
                )
                .path("music[0].performer")
                .entity(String.class)
                .satisfies(music ->
                        assertThat(music)
                                .as("Music performer")
                                .isNotBlank()
                )
                .path("music[0].songUrl")
                .entity(String.class)
                .satisfies(music ->
                        assertThat(music)
                                .as("Music Song URL")
                                .isNotBlank()
                );
    }

    // -------------------------------------------------------------------------
    // Music By Id
    // -------------------------------------------------------------------------

    @Test
    void should_return_music_by_id() {
        Music music = getSingleMusic();

        graphQlTester
                .document("""
                        query GetMusicById($id: ID!) {
                            musicById(id: $id) {
                                id
                                title
                                performer
                                songUrl
                            }
                        }
                        """)
                .variable("id", music.getId())
                .execute()
                .path("musicById.id")
                .entity(String.class)
                .isEqualTo(music.getId().toString())
                .path("musicById.title")
                .entity(String.class)
                .isEqualTo(music.getTitle())
                .path("musicById.performer")
                .entity(String.class)
                .isEqualTo(music.getPerformer())
                .path("musicById.songUrl")
                .entity(String.class)
                .isEqualTo(music.getSongUrl());
    }

    @Test
    void should_return_null_when_music_id_does_not_exist() {
        UUID musicId = UUID.randomUUID();

        graphQlTester
                .document("""
                        query GetMusicById($id: ID!) {
                            musicById(id: $id) {
                                id
                                title
                            }
                        }
                        """)
                .variable("id", musicId.toString())
                .execute()
                .errors()
                .verify()
                .path("musicById")
                .valueIsNull();
    }

    // -------------------------------------------------------------------------
    // Music By performer
    // -------------------------------------------------------------------------

    @Test
    void should_return_music_by_performer() {
        Music music = getSingleMusic("Shirley Bassey");

        graphQlTester
                .document("""
                        query GetMusicByPerformer($performer: String!) {
                            musicByPerformer(performer: $performer) {
                                id
                                title
                                performer
                                songUrl
                            }
                        }
                        """)
                .variable("performer", music.getPerformer())
                .execute()
                .path("musicByPerformer[0].id")
                .entity(String.class)
                .isEqualTo(music.getId().toString())
                .path("musicByPerformer[0].title")
                .entity(String.class)
                .isEqualTo(music.getTitle())
                .path("musicByPerformer[0].performer")
                .entity(String.class)
                .isEqualTo(music.getPerformer())
                .path("musicByPerformer[0].songUrl")
                .entity(String.class)
                .isEqualTo(music.getSongUrl());
    }

    @Test
    void should_return_empty_list_when_music_by_performer_does_not_exist() {
        String performer = "Wrong performer";

        graphQlTester
                .document("""
                        query GetMusicByPerformer($performer: String!) {
                            musicByPerformer(performer: $performer) {
                                id
                                title
                            }
                        }
                        """)
                .variable("performer", performer)
                .execute()
                .path("musicByPerformer")
                .entityList(Object.class)
                .hasSize(0);
    }
}
