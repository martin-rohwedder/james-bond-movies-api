package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;

public record ProducerResponseDto(
        String name,
        String biography,
        String nationality,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath
)
{}
