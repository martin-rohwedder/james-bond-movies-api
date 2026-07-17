package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorResponseDto directorToDirectorResponseDto(Director director);
}
