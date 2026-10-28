package software.ulpgc.chess.infrastructure.adapters.out;

import software.ulpgc.chess.application.domain.Game;
import software.ulpgc.chess.application.ports.out.GameRepositoryPort;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGameRepository implements GameRepositoryPort {

    private final Map<String, Game> database = new ConcurrentHashMap<>();

    @Override
    public void save(Game game) {
        database.put(game.id(), game);
    }

    @Override
    public Optional<Game> findById(String gameId) {
        return Optional.ofNullable(database.get(gameId));
    }
}
