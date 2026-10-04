package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ActorGraphQLController {
    private final ActorService actorService;

    @QueryMapping
    public List<ActorWithMovieResponseDto> actors() {
        return actorService.getAllActors(null, true);
    }

    @QueryMapping
    public ActorWithMovieResponseDto actor(@Argument UUID id) {
        return actorService.getActorByIdWithMovies(id).orElse(null);
    }

    @QueryMapping
    public List<ActorWithMovieResponseDto> actorByName(@Argument String name) {
        return actorService.getAllActors(name, true);
    }
}
