package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.*;
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
    public ResponseEntity<MovieResponseDto> getMovieById(
            @PathVariable UUID id,
            @RequestParam(name = "excludeActors", defaultValue = "false") boolean excludeActors,
            @RequestParam(name = "excludeProducers", defaultValue = "false") boolean excludeProducers,
            @RequestParam(name = "excludeTrivias", defaultValue = "false") boolean excludeTrivias
    )
    {
        return movieService.getMovieById(id, excludeActors, excludeProducers, excludeTrivias)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: /api/movies/{id}/trivias
    @Operation(summary = "Get movie by id with trivias only")
    @GetMapping("/{id}/trivias")
    public ResponseEntity<MovieWithTriviaResponseDto> getMovieWithTriviaById(@PathVariable UUID id) {
        return movieService.getMovieWithTriviaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: /api/movies/{id}/actors
    @Operation(summary = "Get movie by id with actors only")
    @GetMapping("/{id}/actors")
    public ResponseEntity<MovieWithActorsResponseDto>  getMovieWithActorsById(@PathVariable UUID id) {
        return movieService.getMovieWithActorsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: /api/movies/{id}/director
    @Operation(summary = "Get movie by id with director only")
    @GetMapping("/{id}/director")
    public ResponseEntity<MovieWithDirectorResponseDto>  getMovieWithDirectorsById(@PathVariable UUID id) {
        return movieService.getMovieWithDirectorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: /api/movies/{id}/producers
    @Operation(summary = "Get movie by id with producers only")
    @GetMapping("/{id}/producers")
    public ResponseEntity<MovieWithProducersResponseDto>  getMovieWithProducersById(@PathVariable UUID id) {
        return movieService.getMovieWithProducersById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: /api/movies/{id}/writers
    @Operation(summary = "Get a movie by id with writers only")
    @GetMapping("/{id}/writers")
    public ResponseEntity<MovieWithWritersResponseDto>  getMovieWithWritersById(@PathVariable UUID id) {
        return movieService.getMovieWithWritersById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
