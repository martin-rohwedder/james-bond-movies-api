package dk.martinrohwedder.james_bond_movies_api.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MovieResponseDto(
        UUID id,
        int movieNumber,
        String title,
        String shortDescription,
        String longDescription,
        String trailerUrl,
        String worldPremiere,
        String contentRating,
        ParentsGuideResponseDto parentsGuide,
        List<ReleaseDateResponseDto> releaseDates,
        MusicResponseDto music,
        String jamesBondActor,
        DirectorResponseDto director,
        List<ProducerResponseDto> producers,
        String locations,
        List<ActorResponseDto> actors,
        BoxOfficeResponseDto boxOffice,
        TechnicalSpecificationsResponseDto technicalSpecifications,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{}
