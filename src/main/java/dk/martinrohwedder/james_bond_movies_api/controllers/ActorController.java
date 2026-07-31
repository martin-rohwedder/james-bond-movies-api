package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/actors")
@AllArgsConstructor
public class ActorController {
    private final ActorService actorService;

    // GET: /api/actors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDto> getActorById(@PathVariable UUID id) {
        return actorService.getActorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
