package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.BoxOfficeResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.BoxOffice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoxOfficeMapper {
    BoxOfficeResponseDto BoxOfficeToBoxOfficeResponseDto(BoxOffice boxOffice);
}
