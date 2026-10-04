package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WriterRepository extends JpaRepository<Writer, UUID> {
    @EntityGraph(attributePaths = "movies")
    Optional<Writer> findWithMoviesById(UUID id);

    @EntityGraph(attributePaths = "movies")
    List<Writer> findAllByOrderByNameAsc();

    @EntityGraph(attributePaths = "movies")
    List<Writer> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
