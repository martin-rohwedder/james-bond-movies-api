package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProducerGraphQLController {
    private final ProducerService producerService;

    @QueryMapping
    public List<ProducerWithMoviesResponseDto> producers() {
        return producerService.getAllProducers(null, true);
    }

    @QueryMapping
    public ProducerWithMoviesResponseDto producer(@Argument UUID id) {
        return producerService.getProducerByIdWithMovies(id).orElse(null);
    }

    @QueryMapping
    public List<ProducerWithMoviesResponseDto> producerByName(@Argument String name) {
        return producerService.getAllProducers(name, true);
    }
}
