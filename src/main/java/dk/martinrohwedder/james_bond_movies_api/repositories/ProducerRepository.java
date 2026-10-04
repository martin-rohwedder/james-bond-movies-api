package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProducerRepository extends JpaRepository<Producer, UUID> {
    @EntityGraph(attributePaths = "movies")
    Optional<Producer> findWithMoviesById(UUID id);

    @EntityGraph(attributePaths = "movies")
    List<Producer> findAllByOrderByNameAsc();

    @EntityGraph(attributePaths = "movies")
    List<Producer> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
