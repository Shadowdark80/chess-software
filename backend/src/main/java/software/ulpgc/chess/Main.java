package software.ulpgc.chess;

import software.ulpgc.chess.application.domain.Game;
import software.ulpgc.chess.application.ports.out.GameRepositoryPort;
import software.ulpgc.chess.application.usecases.CreateGameUseCase;
import software.ulpgc.chess.infrastructure.adapters.out.InMemoryGameRepository;

public class Main {
    static void main() {
        GameRepositoryPort repository = new InMemoryGameRepository();

        CreateGameUseCase createGameUseCase = new CreateGameUseCase(repository);

        System.out.println("Creando nueva sala de ajedrez...");
        Game game = createGameUseCase.execute("user-1", "Carlos");

        System.out.println("Sala creada con éxito:");
        System.out.println("- ID Sala: " + game.id());
        System.out.println("- Jugador Blancas: " + game.whitePlayer().username());
        System.out.println("- Estado: " + game.state());
        System.out.println("- Tablero inicializado: " + (game.currentBoard() != null ? "Sí (32 piezas)" : "No"));
        System.out.println("- Pieza en e2: " + game.currentBoard().getPieceAt(
                software.ulpgc.chess.application.domain.Coordinates.fromString("e2")
        ).get());
    }
}
