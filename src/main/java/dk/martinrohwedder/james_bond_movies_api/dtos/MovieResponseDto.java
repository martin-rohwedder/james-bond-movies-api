package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovieResponseDto(
        UUID id,
        String title,
        String shortDescription,
        String longDescription,
        String trailerUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{}
