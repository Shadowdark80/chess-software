package software.ulpgc.chess.application.domain;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private BoardFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Board createStandardBoard() {
        Map<Coordinates, Piece> pieces = new HashMap<>();

        setupPawns(pieces);

        setupMajorAndMinorPieces(pieces, PieceColor.WHITE, Rank.RANK_1);
        setupMajorAndMinorPieces(pieces, PieceColor.BLACK, Rank.RANK_8);

        return Board.create(pieces);
    }

    private static void setupPawns(Map<Coordinates, Piece> pieces) {
        for (File file : File.values()) {
            pieces.put(new Coordinates(file, Rank.RANK_2), new Piece(PieceType.PAWN, PieceColor.WHITE));
            pieces.put(new Coordinates(file, Rank.RANK_7), new Piece(PieceType.PAWN, PieceColor.BLACK));
        }
    }

    private static void setupMajorAndMinorPieces(Map<Coordinates, Piece> pieces, PieceColor color, Rank rank) {

        pieces.put(new Coordinates(File.A, rank), new Piece(PieceType.ROOK, color));
        pieces.put(new Coordinates(File.H, rank), new Piece(PieceType.ROOK, color));

        pieces.put(new Coordinates(File.B, rank), new Piece(PieceType.KNIGHT, color));
        pieces.put(new Coordinates(File.G, rank), new Piece(PieceType.KNIGHT, color));

        pieces.put(new Coordinates(File.C, rank), new Piece(PieceType.BISHOP, color));
        pieces.put(new Coordinates(File.F, rank), new Piece(PieceType.BISHOP, color));

        pieces.put(new Coordinates(File.D, rank), new Piece(PieceType.QUEEN, color));

        pieces.put(new Coordinates(File.E, rank), new Piece(PieceType.KING, color));
    }
}
