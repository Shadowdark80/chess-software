package software.ulpgc.chess.application.ports.out;

import software.ulpgc.chess.application.domain.Game;
import java.util.Optional;

public interface GameRepositoryPort {
    void save(Game game);
    Optional<Game> findById(String gameId);
}
