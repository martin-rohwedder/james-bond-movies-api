package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ActorWithMovieResponseDto(
        UUID id,
        String name,
        String characterRole,
        String biography,
        String nationality,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath,
        List<MinimalMovieResponseDto> movies
) {}
