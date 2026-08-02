package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
    List<Actor> findAllByOrderByNameAsc();
    List<Actor> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
