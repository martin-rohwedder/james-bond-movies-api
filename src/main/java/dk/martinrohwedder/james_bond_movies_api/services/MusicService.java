package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.MusicMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.MusicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MusicService {
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;

    public List<MusicResponseDto> getAllMusic(String performer) {
        log.debug("Fetching all music for performer: {}", performer);

        var music = (performer == null || performer.isBlank())
                ? musicRepository.findAllByOrderByPerformerAsc()
                : musicRepository.findAllByPerformerIgnoreCaseOrderByPerformerAsc(performer);

        return music.stream()
                .map(musicMapper::musicToMusicResponseDto)
                .toList();
    }

    public Optional<MusicResponseDto> getMusicById(@PathVariable UUID id) {
        log.debug("Fetching music by id: {}", id);

        return musicRepository.findById(id)
                .map(musicMapper::musicToMusicResponseDto);
    }
}
