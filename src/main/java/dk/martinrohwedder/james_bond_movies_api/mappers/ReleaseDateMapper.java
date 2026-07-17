package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ReleaseDateResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.ReleaseDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReleaseDateMapper {
    ReleaseDateResponseDto releaseDateToReleaseDateResponseDto(ReleaseDate releaseDate);
}
