package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.GenreResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreResponseDto genreToGenreResponseDto(Genre genre);
}
