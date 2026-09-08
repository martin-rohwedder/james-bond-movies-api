package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerResponseDto;
import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    ProducerResponseDto producerToProducerResponseDto(Producer producer);
    ProducerWithMoviesResponseDto producerToProducerWithMoviesResponseDto(Producer producer);

    default ProducerWithMoviesResponseDto producerToProducerWithMoviesResponseDto(
            Producer producer,
            boolean includeMovies
    )
    {
        ProducerWithMoviesResponseDto dto = producerToProducerWithMoviesResponseDto(producer);

        if (!includeMovies) {
            return new ProducerWithMoviesResponseDto(
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
