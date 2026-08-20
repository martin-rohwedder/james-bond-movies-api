package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ParentsGuideResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.ParentsGuide;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParentsGuideMapper {
    ParentsGuideResponseDto parentsGuideToParentsGuideResponseDto(ParentsGuide parentsGuide);
}
