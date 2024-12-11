package com.projeto.listaJogosZerados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Endpoint para listar todos os jogos
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.listGames();
    }

    // Endpoint para adicionar um jogo
    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game createdGame = gameService.addGame(game);
        return ResponseEntity.status(201).body(createdGame);  
    }

    // Endpoint para editar um jogo
    @PutMapping("/{id}")
    public ResponseEntity<Game> editGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        Game game = gameService.editGame(id, updatedGame);
        if (game != null) {
            return ResponseEntity.ok(game); 
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        if (gameService.deleteGame(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
