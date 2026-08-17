package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.TechnicalSpecificationsResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.TechnicalSpecifications;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalSpecificationsMapper {
    TechnicalSpecificationsResponseDto TechnicalSpecificationsToTechnicalSpecificationsResponseDto(TechnicalSpecifications technicalSpecifications);
}
