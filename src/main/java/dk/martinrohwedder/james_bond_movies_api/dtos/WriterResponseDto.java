package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record WriterResponseDto(
        UUID id,
        String name,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath
) {}
