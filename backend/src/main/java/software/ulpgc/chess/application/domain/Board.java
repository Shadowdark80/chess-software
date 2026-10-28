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

        // 1. Create a mutable copy of the current state
        Map<Coordinates, Piece> newPieces = new HashMap<>(this.pieces);

        // 2. Remove the piece from the origin square
        newPieces.remove(move.fromSquare());

        // 3. Place the piece in the destination square (capturing any existing piece implicitly)
        newPieces.put(move.toSquare(), movingPiece);

        // 4. Return a brand new immutable Board
        return new Board(newPieces);
    }
}