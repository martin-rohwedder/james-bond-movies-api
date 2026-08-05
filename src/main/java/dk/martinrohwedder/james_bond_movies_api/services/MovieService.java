package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.MovieMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    /**
     * Get a list of all the movies
     */
    public List<MovieResponseDto> getAllMovies(boolean excludeActors, boolean excludeProducers) {
        return movieRepository.findAllByOrderByMovieNumberAsc()
                .stream()
                .map(movie -> movieMapper.movieToMovieResponseDto(
                        movie,
                        excludeActors,
                        excludeProducers
                ))
                .toList();
    }

    /**
     * Get a specific movie with all its data by id
     */
    public Optional<MovieResponseDto> getMovieById(UUID id) {
        log.debug("Fetching movie with id {}", id);

        return movieRepository.findById(id)
                .map(movieMapper::movieToMovieResponseDto);
    }
}
