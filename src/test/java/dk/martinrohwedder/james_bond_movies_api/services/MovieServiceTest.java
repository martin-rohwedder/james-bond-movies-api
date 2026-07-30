package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.mappers.MovieMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    private MovieService movieService;

    // Helper method for reducing redundancy when creating a Movie object
    private Movie createMovieEntity() {
        return Movie.builder()
                .movieNumber(1)
                .title("Dr. No")
                .build();
    }

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository, movieMapper);
    }

    @Test
    void should_return_all_movies_without_exclusions() {
        // Arrange
        Movie movie = createMovieEntity();
        MovieResponseDto dto = mock(MovieResponseDto.class);

        when(movieRepository.findAllByOrderByMovieNumberAsc())
                .thenReturn(List.of(movie));

        when(movieMapper.movieToMovieResponseDto(movie, false, false))
                .thenReturn(dto);

        // Act
        List<MovieResponseDto> result = movieService.getAllMovies(false, false);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(movieRepository).findAllByOrderByMovieNumberAsc();
        verify(movieMapper).movieToMovieResponseDto(movie, false, false);
        verifyNoMoreInteractions(movieRepository, movieMapper);
    }

    @Test
    void should_call_repository_only_once_when_getting_all_movies() {
        Movie movie = createMovieEntity();
        MovieResponseDto dto = mock(MovieResponseDto.class);

        when(movieRepository.findAllByOrderByMovieNumberAsc())
                .thenReturn(List.of(movie));
        when(movieMapper.movieToMovieResponseDto(movie, false, false))
                .thenReturn(dto);

        movieService.getAllMovies(false, false);

        verify(movieRepository, times(1)).findAllByOrderByMovieNumberAsc();
    }

    @Test
    void should_pass_exclude_actors_flag_to_mapper() {
        // Arrange
        Movie movie = createMovieEntity();
        MovieResponseDto dto = mock(MovieResponseDto.class);

        when(movieRepository.findAllByOrderByMovieNumberAsc())
                .thenReturn(List.of(movie));

        when(movieMapper.movieToMovieResponseDto(movie, true, false))
                .thenReturn(dto);

        // Act
        movieService.getAllMovies(true, false);

        // Assert
        verify(movieRepository).findAllByOrderByMovieNumberAsc();
        verify(movieMapper).movieToMovieResponseDto(movie, true, false);
        verifyNoMoreInteractions(movieRepository, movieMapper);
    }

    @Test
    void should_pass_exclude_producers_flag_to_mapper() {
        // Arrange
        Movie movie = createMovieEntity();
        MovieResponseDto dto = mock(MovieResponseDto.class);

        when(movieRepository.findAllByOrderByMovieNumberAsc())
                .thenReturn(List.of(movie));

        when(movieMapper.movieToMovieResponseDto(movie, false, true))
                .thenReturn(dto);

        // Act
        movieService.getAllMovies(false, true);

        // Assert
        verify(movieRepository).findAllByOrderByMovieNumberAsc();
        verify(movieMapper).movieToMovieResponseDto(movie, false, true);
        verifyNoMoreInteractions(movieRepository, movieMapper);
    }

    @Test
    void should_pass_both_exclusion_flags_to_mapper() {
        // Arrange
        Movie movie = createMovieEntity();
        MovieResponseDto dto = mock(MovieResponseDto.class);

        when(movieRepository.findAllByOrderByMovieNumberAsc())
                .thenReturn(List.of(movie));

        when(movieMapper.movieToMovieResponseDto(movie, true, true))
                .thenReturn(dto);

        // Act
        movieService.getAllMovies(true, true);

        // Assert
        verify(movieRepository).findAllByOrderByMovieNumberAsc();
        verify(movieMapper).movieToMovieResponseDto(movie, true, true);
        verifyNoMoreInteractions(movieRepository, movieMapper);
    }

    @Test
    void should_map_all_movies_in_order() {
        // Arrange
        Movie movie1 = Movie.builder().movieNumber(1).title("Dr. No").build();
        Movie movie2 = Movie.builder().movieNumber(2).title("From Russia With Love").build();

        MovieResponseDto dto1 = mock(MovieResponseDto.class);
        MovieResponseDto dto2 = mock(MovieResponseDto.class);

        when(movieRepository.findAllByOrderByMovieNumberAsc())
                .thenReturn(List.of(movie1, movie2));

        when(movieMapper.movieToMovieResponseDto(movie1, false, false)).thenReturn(dto1);
        when(movieMapper.movieToMovieResponseDto(movie2, false, false)).thenReturn(dto2);

        // Act
        List<MovieResponseDto> result = movieService.getAllMovies(false, false);

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(movieRepository).findAllByOrderByMovieNumberAsc();
        verify(movieMapper).movieToMovieResponseDto(movie1, false, false);
        verify(movieMapper).movieToMovieResponseDto(movie2, false, false);
        verifyNoMoreInteractions(movieRepository, movieMapper);
    }

    @Test
    void should_return_movie_by_id() {
        // Arrange
        UUID id = UUID.randomUUID();
        Movie movie = createMovieEntity();
        MovieResponseDto dto = mock(MovieResponseDto.class);

        when(movieRepository.findById(id))
                .thenReturn(Optional.of(movie));

        when(movieMapper.movieToMovieResponseDto(movie))
                .thenReturn(dto);

        // Act
        Optional<MovieResponseDto> result = movieService.getMovieById(id);

        // Assert
        assertThat(result).contains(dto);

        verify(movieRepository).findById(id);
        verify(movieMapper).movieToMovieResponseDto(movie);
        verifyNoMoreInteractions(movieRepository, movieMapper);
    }

    @Test
    void should_return_empty_optional_when_movie_is_not_found() {
        // Arrange
        UUID id = UUID.randomUUID();

        when(movieRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        Optional<MovieResponseDto> result = movieService.getMovieById(id);

        // Assert
        assertThat(result).isEmpty();

        verify(movieRepository).findById(id);
        verifyNoInteractions(movieMapper);
    }
}
