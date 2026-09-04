package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.WriterResponseDto;
import dk.martinrohwedder.james_bond_movies_api.dtos.WriterWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WriterMapper {
    WriterResponseDto writerToWriterResponseDto(Writer writer);
    WriterWithMovieResponseDto writerToWriterWithMovieResponseDto(Writer writer);

    default WriterWithMovieResponseDto writerToWriterWithMovieResponseDto(
            Writer writer,
            boolean includeMovies
    )
    {
        WriterWithMovieResponseDto dto = writerToWriterWithMovieResponseDto(writer);

        if (!includeMovies) {
            return new WriterWithMovieResponseDto(
                    dto.id(),
                    dto.name(),
                    dto.dateOfBirth(),
                    dto.dateOfDeath(),
                    List.of()
            );
        }

        return dto;
    }
}
