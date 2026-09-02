package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/music")
@AllArgsConstructor
@Tag(name = "Music", description = "Operations related to James Bond movie music")
public class MusicController {
    private final MusicService musicService;

    // GET: /api/music
    @Operation(summary = "Get all music. Filter by performer")
    @GetMapping
    public ResponseEntity<List<MusicResponseDto>> getAllMusic(@RequestParam(required = false) String performer) {
        return ResponseEntity.ok(musicService.getAllMusic(performer));
    }
}
