package software.ulpgc.chess.application.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record Board(Map<Coordinates, Piece> pieces) {

    public Board {
        if (pieces == null) {
            throw new IllegalArgumentException("Pieces map cannot be null.");
        }
        pieces = Map.copyOf(pieces);
    }

    public static Board empty() {
        return new Board(Collections.emptyMap());
    }

     public static Board create(Map<Coordinates, Piece> startingPieces) {
        return new Board(startingPieces);
    }

    public Optional<Piece> getPieceAt(Coordinates coordinates) {
        return Optional.ofNullable(pieces.get(coordinates));
    }

    public Board applyMove(Move move) {
        Piece movingPiece = pieces.get(move.fromSquare());

        if (movingPiece == null) {
            throw new IllegalStateException("Cannot make a move. No piece at origin: " + move.fromSquare());
        }

        Map<Coordinates, Piece> newPieces = new HashMap<>(this.pieces);

        newPieces.remove(move.fromSquare());

        newPieces.put(move.toSquare(), movingPiece);

        return new Board(newPieces);
    }
}