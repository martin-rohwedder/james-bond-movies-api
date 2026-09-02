package dk.martinrohwedder.james_bond_movies_api.controllers;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.services.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/music")
@AllArgsConstructor
@Tag(name = "Music", description = "Operations related to James Bond movie music")
public class MusicController {
    private final MusicService musicService;

    // GET: /api/music
    // GET: /api/music?performer={performer_name}
    @Operation(summary = "Get all music. Filter by performer")
    @GetMapping
    public ResponseEntity<List<MusicResponseDto>> getAllMusic(@RequestParam(required = false) String performer) {
        return ResponseEntity.ok(musicService.getAllMusic(performer));
    }

    // GET: /api/music/{id}
    @Operation(summary = "Get music by id")
    @GetMapping("/{id}")
    public ResponseEntity<MusicResponseDto> getMusicById(@PathVariable UUID id) {
        return musicService.getMusicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
