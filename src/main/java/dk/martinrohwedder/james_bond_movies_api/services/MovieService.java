package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.MovieMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    /**
     * Get a list of all the movies
     */
    public Iterable<MovieResponseDto> getAllMovies(boolean excludeActors, boolean excludeProducers) {
        var movies = movieRepository.findAllByOrderByMovieNumberAsc()
                .stream()
                .map(movieMapper::movieToMovieResponseDto)
                .toList();

        if (excludeActors) {
            for (var movie : movies) {
                movie.actors().clear();
            }
        }

        if (excludeProducers) {
            for (var movie : movies) {
                movie.producers().clear();
            }
        }

        return movies;
    }

    /**
     * Get a specific movie with all its data by id
     */
    public MovieResponseDto getMovieById(UUID id) {
        var movie = movieRepository.findById(id);
        return movie.map(movieMapper::movieToMovieResponseDto).orElse(null);
    }
}
