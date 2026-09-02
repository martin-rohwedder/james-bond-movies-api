package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.MusicMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.MusicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MusicService {
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;

    public List<MusicResponseDto> getAllMusic(String performer) {
        log.debug("Fetching all music for performer: {}", performer);

        var music = (performer == null || performer.isBlank())
                ? musicRepository.findAll()
                : musicRepository.findAllByPerformerIgnoreCase(performer);

        return music.stream()
                .map(musicMapper::musicToMusicResponseDto)
                .toList();
    }
}
