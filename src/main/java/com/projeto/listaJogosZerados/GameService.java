package com.projeto.listaJogosZerados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // Listar todos os jogos
    public List<Game> listGames() {
        return gameRepository.findAll();
    }

    // Adicionar um jogo
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    // Editar um jogo
    public Game editGame(Long id, Game updatedGame) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if (existingGame.isPresent()) {
            Game game = existingGame.get();
            game.setName(updatedGame.getName());
            game.setCompletionDate(updatedGame.getCompletionDate());
            game.setPlaytime(updatedGame.getPlaytime());
            return gameRepository.save(game);
        }
        return null;
    }
    
    public boolean deleteGame(Long id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
