package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record MovieResponseDto(
        UUID id,
        int movieNumber,
        String title,
        String shortDescription,
        String longDescription,
        String trailerUrl,
        String worldPremiere,
        Set<ReleaseDateResponseDto> releaseDates,
        MusicResponseDto music,
        DirectorResponseDto director,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{}
