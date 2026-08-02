package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import dk.martinrohwedder.james_bond_movies_api.mappers.DirectorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
        List<DirectorWithMoviesResponseDto> result = directorService.getAllDirectors();

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(directorRepository).findAllByOrderByNameAsc();
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director1);
        verify(directorMapper).directorToDirectorWithMoviesResponseDto(director2);
        verifyNoMoreInteractions(directorRepository, directorMapper);
    }

    @Test
    void should_return_empty_list_when_no_directors_exist() {
        // Arrange
        when(directorRepository.findAllByOrderByNameAsc())
                .thenReturn(Collections.emptyList());

        // Act
        List<DirectorWithMoviesResponseDto> result = directorService.getAllDirectors();

        // Assert
        assertThat(result).isEmpty();

        verify(directorRepository).findAllByOrderByNameAsc();
        verifyNoInteractions(directorMapper);
    }

    @Test
    void should_map_all_directors_in_order() {
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
        List<DirectorWithMoviesResponseDto> result = directorService.getAllDirectors();

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(directorMapper, times(2))
                .directorToDirectorWithMoviesResponseDto(any(Director.class));
    }
}