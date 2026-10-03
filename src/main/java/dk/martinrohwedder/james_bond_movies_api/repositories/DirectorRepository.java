package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
    @EntityGraph(attributePaths = "movies")
    Optional<Director> findWithMoviesById(UUID id);

    @EntityGraph(attributePaths = "movies")
    List<Director> findAllByOrderByNameAsc();

    @EntityGraph(attributePaths = "movies")
    List<Director> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
