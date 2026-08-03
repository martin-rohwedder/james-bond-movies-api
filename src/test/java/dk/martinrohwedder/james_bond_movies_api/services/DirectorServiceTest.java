package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import dk.martinrohwedder.james_bond_movies_api.mappers.DirectorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private DirectorMapper directorMapper;

    private DirectorService directorService;

    private Director createDirectorEntity(String name) {
        return Director.builder()
                .name(name)
                .nationality("British")
                .build();
    }

    private DirectorWithMoviesResponseDto createDirectorDto(String name) {
        return new DirectorWithMoviesResponseDto(
                UUID.randomUUID(),
                name,
                null,
                "British",
                LocalDate.of(1920, 1, 1),
                null,
                List.of()
        );
    }

    @BeforeEach
    void setUp() {
        directorService = new DirectorService(directorRepository, directorMapper);
    }

    // -------------------------------------------------------------------------
    // getDirectorById
    // -------------------------------------------------------------------------

    @Test
    void should_return_director_by_id() {
        UUID id = UUID.randomUUID();
        Director director = createDirectorEntity("Guy Hamilton");
        DirectorWithMoviesResponseDto dto = createDirectorDto("Guy Hamilton");

        when(directorRepository.findById(id)).thenReturn(Optional.of(director));
        when(directorMapper.directorToDirectorWithMoviesResponseDto(director))
                .thenReturn(dto);

        Optional<DirectorWithMoviesResponseDto> result =
                directorService.getDirectorById(id);

        assertThat(result).contains(dto);

        verify(directorRepository).findById(id);
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director);
        verifyNoMoreInteractions(directorRepository, directorMapper);
    }

    @Test
    void should_return_empty_optional_when_director_is_not_found() {
        UUID id = UUID.randomUUID();

        when(directorRepository.findById(id)).thenReturn(Optional.empty());

        Optional<DirectorWithMoviesResponseDto> result =
                directorService.getDirectorById(id);

        assertThat(result).isEmpty();

        verify(directorRepository).findById(id);
        verifyNoInteractions(directorMapper);
    }

    // -------------------------------------------------------------------------
    // getAllDirectors
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_directors() {
        // Arrange
        Director director1 = createDirectorEntity("Guy Hamilton");
        Director director2 = createDirectorEntity("Lewis Gilbert");

        DirectorWithMoviesResponseDto dto1 = createDirectorDto("Guy Hamilton");
        DirectorWithMoviesResponseDto dto2 = createDirectorDto("Lewis Gilbert");

        when(directorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(director1, director2));

        when(directorMapper.directorToDirectorWithMoviesResponseDto(director1))
                .thenReturn(dto1);
        when(directorMapper.directorToDirectorWithMoviesResponseDto(director2))
                .thenReturn(dto2);

        // Act
        List<DirectorWithMoviesResponseDto> result = directorService.getAllDirectors(null);

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(directorRepository).findAllByOrderByNameAsc();
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director1);
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director2);
        verifyNoMoreInteractions(directorRepository, directorMapper);
    }

    @Test
    void should_return_directors_filtered_by_name() {
        Director director = createDirectorEntity("Guy Hamilton");
        DirectorWithMoviesResponseDto dto = createDirectorDto("Guy Hamilton");

        when(directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Guy Hamilton"))
                .thenReturn(List.of(director));

        when(directorMapper.directorToDirectorWithMoviesResponseDto(director))
                .thenReturn(dto);

        List<DirectorWithMoviesResponseDto> result = directorService.getAllDirectors("Guy Hamilton");

        assertThat(result).containsExactly(dto);

        verify(directorRepository).findAllByNameIgnoreCaseOrderByNameAsc("Guy Hamilton");
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director);
        verifyNoMoreInteractions(directorRepository, directorMapper);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void should_return_all_directors_when_name_is_blank_or_null(String name) {
        Director director = createDirectorEntity("Guy Hamilton");
        DirectorWithMoviesResponseDto dto = createDirectorDto("Guy Hamilton");

        when(directorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(director));

        when(directorMapper.directorToDirectorWithMoviesResponseDto(director))
                .thenReturn(dto);

        List<DirectorWithMoviesResponseDto> result =
                directorService.getAllDirectors(name);

        assertThat(result).containsExactly(dto);

        verify(directorRepository).findAllByOrderByNameAsc();
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director);
        verifyNoMoreInteractions(directorRepository, directorMapper);
    }

    @Test
    void should_return_empty_list_when_no_matching_director_exists() {
        when(directorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Unknown Director"))
                .thenReturn(Collections.emptyList());

        List<DirectorWithMoviesResponseDto> result =
                directorService.getAllDirectors("Unknown Director");

        assertThat(result).isEmpty();

        verify(directorRepository).findAllByNameIgnoreCaseOrderByNameAsc("Unknown Director");
        verifyNoInteractions(directorMapper);
    }

    @Test
    void should_return_empty_list_when_no_directors_exist() {
        // Arrange
        when(directorRepository.findAllByOrderByNameAsc())
                .thenReturn(Collections.emptyList());

        // Act
        List<DirectorWithMoviesResponseDto> result = directorService.getAllDirectors(null);

        // Assert
        assertThat(result).isEmpty();

        verify(directorRepository).findAllByOrderByNameAsc();
        verifyNoInteractions(directorMapper);
    }
}