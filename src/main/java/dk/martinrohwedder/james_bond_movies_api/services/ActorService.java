package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.ActorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    /**
     * Get a specific actor by id
     */
    public Optional<ActorWithMovieResponseDto> getActorById(UUID id) {
        log.debug("Fetching actor with id {}", id);

        return actorRepository.findById(id)
                .map(actorMapper::actorToActorWithMovieResponseDto);
    }

    public List<ActorWithMovieResponseDto> getAllActors(String name, boolean includeMovies) {
        var actors = (name == null || name.isBlank())
                ? actorRepository.findAllByOrderByNameAsc()
                : actorRepository.findAllByNameIgnoreCaseOrderByNameAsc(name);

        return actors.stream()
                .map(actor -> actorMapper.actorToActorWithMovieResponseDto(actor, includeMovies))
                .toList();
    }
}
