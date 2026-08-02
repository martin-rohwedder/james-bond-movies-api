package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.ActorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    /**
     * Get a specific actor by id
     */
    public Optional<ActorResponseDto> getActorById(UUID id) {
        return actorRepository.findById(id)
                .map(actorMapper::actorToActorResponseDto);
    }

    /**
     * Get a specific actor by name
     */
    public List<ActorResponseDto> getActorByName(String name) {
        return actorRepository.findAllByNameIgnoreCase(name)
                .stream()
                .map(actorMapper::actorToActorResponseDto)
                .toList();
    }

    /**
     * Get All Actors
     */
    public List<ActorResponseDto> getAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(actorMapper::actorToActorResponseDto)
                .toList();
    }
}
