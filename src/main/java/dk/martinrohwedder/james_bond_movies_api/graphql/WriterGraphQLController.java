package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.dtos.WriterWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WriterGraphQLController {
    private final WriterService writerService;

    @QueryMapping
    public List<WriterWithMovieResponseDto> writers() {
        return writerService.getAllWriters(null, true);
    }

    @QueryMapping
    public WriterWithMovieResponseDto writer(@Argument UUID id) {
        return writerService.getWriterByIdWithMovies(id).orElse(null);
    }

    @QueryMapping
    public List<WriterWithMovieResponseDto> writerByName(@Argument String name) {
        return writerService.getAllWriters(name, true);
    }
}
