package software.ulpgc.chess.application.domain;

public record Game(String id,
                   User whitePlayer,
                   User blackPlayer,
                   GameState state,
                   List<Move> moves,
                   Board currentBoard, // ¡Nuevo!
                   String result) {
}
