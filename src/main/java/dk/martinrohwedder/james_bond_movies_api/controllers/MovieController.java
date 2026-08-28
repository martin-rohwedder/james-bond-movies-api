package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.MovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
@Tag(name = "Movies", description = "Operations related to James Bond movies")
public class MovieController {
    private final MovieService movieService;

    // GET: /api/movies
    @Operation(summary = "Get all movies")
    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies(
            @RequestParam(name = "excludeActors", defaultValue = "false") boolean excludeActors,
            @RequestParam(name = "excludeProducers", defaultValue = "false") boolean excludeProducers,
            @RequestParam(name ="excludeTrivias", defaultValue = "false") boolean excludeTrivias
    )
    {
        return ResponseEntity.ok(movieService.getAllMovies(excludeActors, excludeProducers, excludeTrivias));
    }

    // GET: /api/movies/{id}
    @Operation(summary = "Get movie by id")
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable UUID id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
