package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record ActorResponseDto(
        UUID id,
        String name,
        String characterRole,
        String biography,
        String nationality,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath
)
{}
