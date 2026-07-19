package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    ProducerResponseDto producerToProducerResponseDto(Producer producer);
}
