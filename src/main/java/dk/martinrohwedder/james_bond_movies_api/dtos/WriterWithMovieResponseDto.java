package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record WriterWithMovieResponseDto(
        UUID id,
        String name,
        LocalDate dateOfBirth,
        LocalDate dateOfDeath,
        List<MovieForSpecificWriterResponseDto> movies
) {}
