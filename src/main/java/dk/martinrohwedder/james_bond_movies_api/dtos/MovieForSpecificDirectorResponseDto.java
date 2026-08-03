package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.util.UUID;

public record MovieForSpecificDirectorResponseDto(
        UUID id,
        int movieNumber,
        String title
) {}
