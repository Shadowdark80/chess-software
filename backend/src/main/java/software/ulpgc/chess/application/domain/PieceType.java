package software.ulpgc.chess.application.domain;

public enum PieceType {
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    KING;

    public boolean isPawn() {
        return this == PAWN;
    }

    public boolean isKnight() {
        return this == KNIGHT;
    }

    public boolean isBishop() {
        return this == BISHOP;
    }

    public boolean isRook() {
        return this == ROOK;
    }

    public boolean isQueen() {
        return this == QUEEN;
    }

    public boolean isKing() {
        return this == KING;
    }
}
