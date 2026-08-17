package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                MusicMapper.class,
                DirectorMapper.class,
                ReleaseDateMapper.class,
                ProducerMapper.class,
                ActorMapper.class,
                TechnicalSpecificationsMapper.class,
                BoxOfficeMapper.class
        }
)
public interface MovieMapper {
    MovieResponseDto movieToMovieResponseDto(Movie movie);

    default MovieResponseDto movieToMovieResponseDto(
            Movie movie,
            boolean excludeActors,
            boolean excludeProducers
    ) {
        MovieResponseDto dto = movieToMovieResponseDto(movie);

        return new MovieResponseDto(
                dto.id(),
                dto.movieNumber(),
                dto.title(),
                dto.shortDescription(),
                dto.longDescription(),
                dto.trailerUrl(),
                dto.worldPremiere(),
                dto.releaseDates(),
                dto.music(),
                dto.jamesBondActor(),
                dto.director(),
                excludeProducers ? List.of() : dto.producers(),
                dto.locations(),
                excludeActors ? List.of() : dto.actors(),
                dto.boxOffice(),
                dto.technicalSpecifications(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }
}
