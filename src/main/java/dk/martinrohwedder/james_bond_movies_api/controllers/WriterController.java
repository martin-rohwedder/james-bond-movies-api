package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.WriterWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.WriterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/writers")
@AllArgsConstructor
@Tag(name = "Writers", description = "Operations related to James Bond movie writers")
public class WriterController {
    private final WriterService writerService;

    // GET: /api/writers/{id}
    @Operation(summary = "Get a writer by id")
    @GetMapping("/{id}")
    public ResponseEntity<WriterWithMovieResponseDto> getWriterById(@PathVariable UUID id) {
        return writerService.getWriterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: /api/writers
    // GET: /api/writers?name={writer_name}
    // GET: /api/writers?name={writer_name}&includeMovies={true/false}
    @Operation(summary = "Get all writers. Filter by name and include movies")
    @GetMapping
    public ResponseEntity<List<WriterWithMovieResponseDto>> getAllWriters(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "true") boolean includeMovies
    )
    {
        return ResponseEntity.ok(writerService.getAllWriters(name, includeMovies));
    }
}
