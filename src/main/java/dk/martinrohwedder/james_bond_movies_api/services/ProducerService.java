package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.mappers.ProducerMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.ProducerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProducerService {
    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    /**
     * Get all producers by name
     */
    public List<ProducerWithMoviesResponseDto> getAllProducers(String name) {
        var producers = (name == null || name.isBlank())
                ? producerRepository.findAllByOrderByNameAsc()
                : producerRepository.findAllByNameIgnoreCaseOrderByNameAsc(name);

        return producers.stream()
                .map(producerMapper::producerToProducerWithMoviesResponseDto)
                .toList();
    }

    /**
     * Get a specific producer by id
     */
    public Optional<ProducerWithMoviesResponseDto> getProducerById(UUID id) {
        log.debug("Fetching producer with id {}", id);

        return producerRepository.findById(id)
                .map(producerMapper::producerToProducerWithMoviesResponseDto);
    }
}
