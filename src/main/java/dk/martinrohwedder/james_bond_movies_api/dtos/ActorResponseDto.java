package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;

public record ActorResponseDto(
        String name,
        String characterRole,
        String biography,
        String nationality,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath
)
{}
