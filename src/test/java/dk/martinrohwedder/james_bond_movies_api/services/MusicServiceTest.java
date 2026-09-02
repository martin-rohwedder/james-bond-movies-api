package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import dk.martinrohwedder.james_bond_movies_api.mappers.MusicMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.MusicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MusicServiceTest {
    @Mock
    private MusicRepository musicRepository;

    @Mock
    private MusicMapper musicMapper;

    private MusicService musicService;

    private Music createMusicEntity(String performer) {
        return Music.builder()
                .title("James Bond Music")
                .performer(performer)
                .songUrl("https://song.url")
                .build();
    }

    private MusicResponseDto createMusicDto(String performer) {
        return new MusicResponseDto(
                UUID.randomUUID(),
                "James Bond Music",
                performer,
                "https://song.url"
        );
    }

    @BeforeEach
    void setUp() {
        musicService = new MusicService(musicRepository, musicMapper);
    }

    // -------------------------------------------------------------------------
    // getMusicById
    // -------------------------------------------------------------------------

    @Test
    void should_return_music_by_id() {
        // Arrange
        UUID id = UUID.randomUUID();
        Music music = createMusicEntity("Chris Cornell");
        MusicResponseDto dto = createMusicDto("Chris Cornell");

        when(musicRepository.findById(id))
                .thenReturn(Optional.of(music));

        when(musicMapper.musicToMusicResponseDto(music))
                .thenReturn(dto);

        // Act
        Optional<MusicResponseDto> result = musicService.getMusicById(id);

        // Assert
        assertThat(result).contains(dto);

        verify(musicRepository).findById(id);
        verify(musicMapper).musicToMusicResponseDto(music);
        verifyNoMoreInteractions(musicRepository, musicMapper);
    }

    @Test
    void should_return_empty_optional_when_music_is_not_found() {
        // Arrange
        UUID id = UUID.randomUUID();

        when(musicRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        Optional<MusicResponseDto> result = musicService.getMusicById(id);

        // Assert
        assertThat(result).isEmpty();

        verify(musicRepository).findById(id);
        verifyNoInteractions(musicMapper);
    }

    // -------------------------------------------------------------------------
    // getAllMusic
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_music() {
        // Arrange
        Music music1 = createMusicEntity("Shirley Bassey");
        Music music2 = createMusicEntity("Chris Cornell");

        MusicResponseDto dto1 = createMusicDto("Shirley Bassey");
        MusicResponseDto dto2 = createMusicDto("Chris Cornell");

        when(musicRepository.findAll())
                .thenReturn(List.of(music1, music2));

        when(musicMapper.musicToMusicResponseDto(music1))
                .thenReturn(dto1);
        when(musicMapper.musicToMusicResponseDto(music2))
                .thenReturn(dto2);

        // Act
        List<MusicResponseDto> result = musicService.getAllMusic(null);

        // Assert
        assertThat(result).containsExactlyInAnyOrder(dto1, dto2);

        verify(musicRepository).findAll();
        verify(musicMapper).musicToMusicResponseDto(music1);
        verify(musicMapper).musicToMusicResponseDto(music2);
        verifyNoMoreInteractions(musicRepository, musicMapper);
    }

    @Test
    void should_return_music_filtered_by_performer() {
        // Arrange
        Music music = createMusicEntity("Shirley Bassey");
        MusicResponseDto dto = createMusicDto("Shirley Bassey");

        when(musicRepository.findAllByPerformerIgnoreCase("Shirley Bassey"))
                .thenReturn(List.of(music));

        when(musicMapper.musicToMusicResponseDto(music))
                .thenReturn(dto);

        // Act
        List<MusicResponseDto> result = musicService.getAllMusic("Shirley Bassey");

        // Assert
        assertThat(result).containsExactlyInAnyOrder(dto);

        verify(musicRepository).findAllByPerformerIgnoreCase("Shirley Bassey");
        verify(musicMapper).musicToMusicResponseDto(music);
        verifyNoMoreInteractions(musicRepository, musicMapper);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void should_return_all_music_when_performer_name_is_blank_or_null(String performer) {
        // Arrange
        Music music = createMusicEntity("Chris Cornell");
        MusicResponseDto dto = createMusicDto("Chris Cornell");

        when(musicRepository.findAll())
                .thenReturn(List.of(music));

        when(musicMapper.musicToMusicResponseDto(music))
                .thenReturn(dto);

        // Act
        List<MusicResponseDto> result = musicService.getAllMusic(performer);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(musicRepository).findAll();
        verify(musicMapper).musicToMusicResponseDto(music);
        verifyNoMoreInteractions(musicRepository, musicMapper);
    }

    @Test
    void should_return_empty_list_when_no_matching_music_exists() {
        // Arrange
        when(musicRepository.findAllByPerformerIgnoreCase("Unknown Performer"))
                .thenReturn(Collections.emptyList());

        // Act
        List<MusicResponseDto> result = musicService.getAllMusic("Unknown Performer");

        // Assert
        assertThat(result).isEmpty();

        verify(musicRepository).findAllByPerformerIgnoreCase("Unknown Performer");
        verifyNoInteractions(musicMapper);
    }

    @Test
    void should_return_empty_list_when_no_music_exists() {
        // Arrange
        when(musicRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Act
        List<MusicResponseDto> result = musicService.getAllMusic(null);

        // Assert
        assertThat(result).isEmpty();

        verify(musicRepository).findAll();
        verifyNoInteractions(musicMapper);
    }
}
