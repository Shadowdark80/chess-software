package software.ulpgc.chess.application.usecases;

import software.ulpgc.chess.application.domain.*;
import software.ulpgc.chess.application.ports.out.GameRepositoryPort;

import java.util.UUID;

public class CreateGameUseCase {
    private final GameRepositoryPort gameRepositoryPort;

    public CreateGameUseCase(GameRepositoryPort gameRepositoryPort){
        this.gameRepositoryPort = gameRepositoryPort;
    }

    public Game execute(String creatorId, String creatorName) {
        String roomId = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        User creator = new User(creatorId, creatorName);

        Game newGame = Game.createNew(roomId, creator);

        gameRepositoryPort.save(newGame);

        return newGame;
    }
}
