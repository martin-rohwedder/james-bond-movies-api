package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.DirectorWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
@AllArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    // GET: /api/directors
    // GET: /api/directors?name={director_name}
    @GetMapping
    public ResponseEntity<List<DirectorWithMoviesResponseDto>> getAllDirectors(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(directorService.getAllDirectors(name));
    }
}
