package dk.martinrohwedder.james_bond_movies_api.dtos;

public record BoxOfficeResponseDto(
        Long budgetUsd,
        Long grossRevenueUsAndCanadaUsd,
        Long grossRevenueWorldwideUsd
) {}
