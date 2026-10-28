package software.ulpgc.chess.application.domain;

public record Piece(PieceType type, PieceColor color) {
    public Piece {
        if (type == null || color == null) {
            throw new IllegalArgumentException("Piece type and color cannot be null.");
        }
    }
}
