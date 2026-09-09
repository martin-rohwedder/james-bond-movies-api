package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.util.List;
import java.util.UUID;

public record MovieWithWritersResponseDto(
        UUID id,
        int movieNumber,
        String title,
        List<WriterResponseDto> writers
) {}
