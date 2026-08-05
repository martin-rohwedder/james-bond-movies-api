package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/producers")
@AllArgsConstructor
public class ProducerController {
    private final ProducerService producerService;

    // GET: /api/producers
    // GET: /api/producers?name={producer_name}
    @GetMapping
    public ResponseEntity<List<ProducerWithMoviesResponseDto>> getAllProducers(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(producerService.getAllProducers(name));
    }

    // GET: /api/producers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProducerWithMoviesResponseDto> getProducerById(@PathVariable UUID id) {
        return producerService.getProducerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
