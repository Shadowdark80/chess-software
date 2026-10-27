package software.ulpgc.chess.application.domain;

public enum PieceColor {
    WHITE,
    BLACK;

    public PieceColor getOpposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
