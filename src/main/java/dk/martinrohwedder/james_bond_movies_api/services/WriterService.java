package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.WriterWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.WriterMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.WriterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class WriterService {
    private final WriterRepository writerRepository;
    private final WriterMapper writerMapper;

    /**
     * Get a specific writer by id
     */
    public Optional<WriterWithMovieResponseDto> getWriterById(UUID id) {
        log.debug("Getting writer with id {}", id);

        return writerRepository.findById(id)
                .map(writerMapper::writerToWriterWithMovieResponseDto);
    }

    /**
     * Get All writers or a specific writer by name
     */
    public List<WriterWithMovieResponseDto> getAllWriters(String name, boolean includeMovies) {
        log.debug("Getting all writers with name: {} and includeMovies: {}", name, includeMovies);

        var writers = (name == null || name.isBlank())
                ? writerRepository.findAllByOrderByNameAsc()
                : writerRepository.findAllByNameIgnoreCaseOrderByNameAsc(name);

        return writers
                .stream()
                .map(writer -> writerMapper.writerToWriterWithMovieResponseDto(writer, includeMovies))
                .toList();
    }
}
