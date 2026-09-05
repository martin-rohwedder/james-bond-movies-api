package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProducerWithMoviesResponseDto(
        UUID id,
        String name,
        String biography,
        String nationality,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath,
        List<MinimalMovieResponseDto> movies
) {}
