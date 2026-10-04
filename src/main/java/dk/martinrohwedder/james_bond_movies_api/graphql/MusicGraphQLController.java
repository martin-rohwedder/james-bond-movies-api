package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MusicGraphQLController {
    private final MusicService musicService;

    @QueryMapping
    public List<MusicResponseDto> music() {
        return musicService.getAllMusic(null);
    }

    @QueryMapping
    public MusicResponseDto musicById(@Argument UUID id) {
        return musicService.getMusicById(id).orElse(null);
    }

    @QueryMapping
    public List<MusicResponseDto> musicByPerformer(@Argument String performer) {
        return musicService.getAllMusic(performer);
    }
}
