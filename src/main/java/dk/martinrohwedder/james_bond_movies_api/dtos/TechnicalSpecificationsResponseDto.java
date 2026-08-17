package dk.martinrohwedder.james_bond_movies_api.dtos;

public record TechnicalSpecificationsResponseDto(
        Integer runtimeInMinutes,
        String soundMix,
        String aspectRatio,
        String printedFilmFormat
) {}
