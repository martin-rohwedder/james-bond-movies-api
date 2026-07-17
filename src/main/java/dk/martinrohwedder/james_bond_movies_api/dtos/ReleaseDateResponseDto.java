package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;

public record ReleaseDateResponseDto(
        LocalDate dateOfRelease,
        String country,
        String countryCode
)
{}
