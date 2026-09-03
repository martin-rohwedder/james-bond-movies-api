package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.util.UUID;

public record MusicResponseDto(
        UUID id,
        String title,
        String performer,
        String songUrl
)
{}
