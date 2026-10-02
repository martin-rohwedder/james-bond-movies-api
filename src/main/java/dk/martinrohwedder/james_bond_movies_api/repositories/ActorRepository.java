package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
    @EntityGraph(attributePaths = "movies")
    Optional<Actor> findWithMoviesById(UUID id);

    @EntityGraph(attributePaths = "movies")
    List<Actor> findAllByOrderByNameAsc();

    @EntityGraph(attributePaths = "movies")
    List<Actor> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
