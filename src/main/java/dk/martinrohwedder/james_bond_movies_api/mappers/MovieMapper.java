package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = MusicMapper.class
)
public interface MovieMapper {
    MovieResponseDto movieToMovieResponseDto(Movie movie);
}
