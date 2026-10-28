package software.ulpgc.chess.application.domain;

public record Move(
        Coordinates fromSquare,
        Coordinates toSquare,
        PieceType piece,
        PieceColor color,
        String userId
) {
    public Move {
        if (fromSquare == null || toSquare == null) {
            throw new IllegalArgumentException("Origin and destination squares cannot be null.");
        }
        if (fromSquare.equals(toSquare)) {
            throw new IllegalArgumentException("A move must change the piece's position. Origin and destination are the same: " + fromSquare);
        }
        if (piece == null || color == null) {
            throw new IllegalArgumentException("Moved piece type and color must be specified.");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
    }

    public static Move create(Coordinates from, Coordinates to, PieceType piece, PieceColor color, String userId) {
        return new Move(from, to, piece, color, userId);
    }

    public static Move fromAlgebraic(String fromNotation, String toNotation, PieceType piece, PieceColor color, String userId) {
        Coordinates from = Coordinates.fromString(fromNotation);
        Coordinates to = Coordinates.fromString(toNotation);
        return new Move(from, to, piece, color, userId);
    }
}
