package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProducerRepository extends JpaRepository<Producer, UUID> {
    List<Producer> findAllByOrderByNameAsc();
    List<Producer> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
