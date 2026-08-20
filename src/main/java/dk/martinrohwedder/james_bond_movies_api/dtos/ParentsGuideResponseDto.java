package dk.martinrohwedder.james_bond_movies_api.dtos;

public record ParentsGuideResponseDto(
        String sexAndNudity,
        String violenceAndGore,
        String profanity,
        String alcoholDrugsAndSmoking,
        String frighteningAndIntenseScenes
) {}
