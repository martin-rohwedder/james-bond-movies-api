package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
    List<Director> findAllByOrderByNameAsc();
    List<Director> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
