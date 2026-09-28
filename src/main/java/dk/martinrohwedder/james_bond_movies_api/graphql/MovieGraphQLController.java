package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MovieGraphQLController {
    private final MovieService movieService;

    @QueryMapping
    public List<MovieResponseDto> movies() {
        return movieService.getAllMovies(false, false, false);
    }

    @QueryMapping
    public MovieResponseDto movie(@Argument UUID id) {
        return movieService.getMovieById(id, false, false, false).orElse(null);
    }
}