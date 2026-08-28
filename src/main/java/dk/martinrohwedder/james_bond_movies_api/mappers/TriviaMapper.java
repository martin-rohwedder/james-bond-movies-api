package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.TriviaResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Trivia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TriviaMapper {
    TriviaResponseDto triviaToTriviaResponseDto(Trivia trivia);
}
