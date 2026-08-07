package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/directors")
@AllArgsConstructor
@Tag(name = "Directors", description = "Operations related to James Bond movie directors")
public class DirectorController {
    private final DirectorService directorService;

    // GET: /api/directors
    // GET: /api/directors?name={director_name}
    @Operation(summary = "Get all directors. Filter by name")
    @GetMapping
    public ResponseEntity<List<DirectorWithMoviesResponseDto>> getAllDirectors(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(directorService.getAllDirectors(name));
    }

    // GET: /api/directors/{id}
    @Operation(summary = "Get a director by id")
    @GetMapping("/{id}")
    public ResponseEntity<DirectorWithMoviesResponseDto> getDirectorById(@PathVariable UUID id) {
        return directorService.getDirectorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
