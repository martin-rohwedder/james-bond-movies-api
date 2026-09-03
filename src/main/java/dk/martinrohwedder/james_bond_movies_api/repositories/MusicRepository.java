package dk.martinrohwedder.james_bond_movies_api.repositories;

import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MusicRepository extends JpaRepository<Music, UUID> {
    List<Music> findAllByOrderByPerformerAsc();
    List<Music> findAllByPerformerIgnoreCaseOrderByPerformerAsc(String performer);
}
