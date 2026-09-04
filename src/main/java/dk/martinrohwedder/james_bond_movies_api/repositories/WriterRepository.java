package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WriterRepository extends JpaRepository<Writer, UUID> {
    List<Writer> findAllByOrderByNameAsc();
    List<Writer> findAllByNameIgnoreCaseOrderByNameAsc(String name);
}
