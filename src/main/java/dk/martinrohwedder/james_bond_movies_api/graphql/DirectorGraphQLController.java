package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DirectorGraphQLController {
    private final DirectorService directorService;

    @QueryMapping
    public List<DirectorWithMoviesResponseDto> directors() {
        return directorService.getAllDirectors(null, true);
    }

    @QueryMapping
    public DirectorWithMoviesResponseDto director(@Argument UUID id) {
        return directorService.getDirectorByIdWithMovies(id).orElse(null);
    }

    @QueryMapping
    public List<DirectorWithMoviesResponseDto> directorByName(@Argument String name) {
        return directorService.getAllDirectors(name, true);
    }
}
