package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.dtos.ActorWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorResponseDto actorToActorResponseDto(Actor actor);
    ActorWithMovieResponseDto actorToActorWithMovieResponseDto(Actor actor);

    default ActorWithMovieResponseDto actorToActorWithMovieResponseDto(
            Actor actor,
            boolean includeMovies
    ) {
        ActorWithMovieResponseDto dto = actorToActorWithMovieResponseDto(actor);

        if (!includeMovies) {
            return new ActorWithMovieResponseDto(
                    dto.id(),
                    dto.name(),
                    dto.characterRole(),
                    dto.biography(),
                    dto.nationality(),
                    dto.dateOfBirth(),
                    dto.dateOfDeath(),
                    List.of()
            );
        }

        return dto;
    }
}
