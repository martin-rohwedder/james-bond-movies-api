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
                GenreMapper.class,
                TriviaMapper.class,
                TechnicalSpecificationsMapper.class,
                BoxOfficeMapper.class,
                ParentsGuideMapper.class
        }
)
public interface MovieMapper {
    MovieResponseDto movieToMovieResponseDto(Movie movie);

    default MovieResponseDto movieToMovieResponseDto(
            Movie movie,
            boolean excludeActors,
            boolean excludeProducers,
            boolean excludeTrivias
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
                dto.contentRating(),
                dto.parentsGuide(),
                dto.releaseDates(),
                dto.music(),
                dto.jamesBondActor(),
                dto.genres(),
                dto.director(),
                excludeProducers ? List.of() : dto.producers(),
                dto.locations(),
                excludeActors ? List.of() : dto.actors(),
                excludeTrivias ? List.of() : dto.trivias(),
                dto.boxOffice(),
                dto.technicalSpecifications(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }
}
