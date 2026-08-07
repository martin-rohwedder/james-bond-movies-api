package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.ProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/producers")
@AllArgsConstructor
@Tag(name = "Producers", description = "Operations related to James Bond movie producers")
public class ProducerController {
    private final ProducerService producerService;

    // GET: /api/producers
    // GET: /api/producers?name={producer_name}
    @Operation(summary = "Get all producers. Filter by name")
    @GetMapping
    public ResponseEntity<List<ProducerWithMoviesResponseDto>> getAllProducers(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(producerService.getAllProducers(name));
    }

    // GET: /api/producers/{id}
    @Operation(summary = "Get a producer by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProducerWithMoviesResponseDto> getProducerById(@PathVariable UUID id) {
        return producerService.getProducerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
