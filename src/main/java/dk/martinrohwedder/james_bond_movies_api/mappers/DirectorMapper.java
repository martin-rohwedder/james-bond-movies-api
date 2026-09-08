package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorResponseDto directorToDirectorResponseDto(Director director);
    DirectorWithMoviesResponseDto directorToDirectorWithMoviesResponseDto(Director director);

    default DirectorWithMoviesResponseDto directorToDirectorWithMoviesResponseDto(
            Director director,
            boolean includeMovies
    )
    {
        DirectorWithMoviesResponseDto dto = directorToDirectorWithMoviesResponseDto(director);

        if (!includeMovies) {
            return new DirectorWithMoviesResponseDto(
                    dto.id(),
                    dto.name(),
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
