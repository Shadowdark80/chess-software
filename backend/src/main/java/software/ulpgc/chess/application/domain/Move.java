package software.ulpgc.chess.application.domain;

public record Move(String fromSquare, String toSquare, PieceType piece, PieceColor color, String userId) {

}
