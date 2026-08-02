package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // GET: /api/actors
    // GET: /api/actors?name={actor_name}
    @GetMapping
    public ResponseEntity<List<ActorResponseDto>> getAllActors(
            @RequestParam(name = "name", required = false) String name
    ) {
        if (name == null) {
            return ResponseEntity.ok(actorService.getAllActors());
        }

        return ResponseEntity.ok(actorService.getActorByName(name));
    }
}
