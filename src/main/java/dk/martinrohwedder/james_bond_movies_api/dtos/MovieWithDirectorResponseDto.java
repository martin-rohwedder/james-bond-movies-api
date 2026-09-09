package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.util.UUID;

public record MovieWithDirectorResponseDto(
        UUID id,
        int movieNumber,
        String title,
        DirectorResponseDto director
) {}
