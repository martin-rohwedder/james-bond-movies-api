package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.DirectorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    /**
     * Get all directors
     */
    public List<DirectorWithMoviesResponseDto> getAllDirectors() {
        return directorRepository.findAllByOrderByNameAsc()
                .stream()
                .map(directorMapper::directorToDirectorWithMoviesResponseDto)
                .toList();
    }
}
