package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.WriterResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WriterMapper {
    WriterResponseDto writerToWriterResponseDto(Writer writer);
}
