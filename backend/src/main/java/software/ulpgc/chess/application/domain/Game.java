package software.ulpgc.chess.application.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Game(
        String id,
        User whitePlayer,
        User blackPlayer, // Can be null initially
        GameState state,
        List<Move> moves,
        Board currentBoard,
        PieceColor turn,
        String result // e.g., "WHITE_WINS", "DRAW"
) {

    public Game {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Game ID cannot be null or empty.");
        }
        if (whitePlayer == null) {
            throw new IllegalArgumentException("A game must have at least a white player (creator).");
        }
        if (state == null || currentBoard == null || turn == null) {
            throw new IllegalArgumentException("State, board, and turn cannot be null.");
        }
        moves = List.copyOf(moves != null ? moves : List.of());
    }

    public static Game createNew(String id, User creator) {
        return new Game(
                id,
                creator,
                null,
                GameState.WAITING_FOR_PLAYER,
                List.of(),
                BoardFactory.createStandardBoard(),
                PieceColor.WHITE, // White always moves first
                null
        );
    }

    public Game joinRival(User rival) {
        if (this.state != GameState.WAITING_FOR_PLAYER) {
            throw new IllegalStateException("Cannot join. The game is already in progress or finished.");
        }
        if (rival.id().equals(this.whitePlayer.id())) {
            throw new IllegalArgumentException("Rival cannot have the same ID as the creator.");
        }

        return new Game(
                this.id,
                this.whitePlayer,
                rival,
                GameState.IN_PROGRESS,
                this.moves,
                this.currentBoard,
                this.turn,
                this.result
        );
    }

    public Game registerMove(Move move) {
        if (this.state != GameState.IN_PROGRESS) {
            throw new IllegalStateException("Moves can only be made when the game is in progress.");
        }

        validateTurn(move.userId());

        if (move.color() != this.turn) {
            throw new IllegalArgumentException("It is " + this.turn + "'s turn, but the move was for " + move.color());
        }

        Board updatedBoard = this.currentBoard.applyMove(move);

        List<Move> newMoves = new ArrayList<>(this.moves);
        newMoves.add(move);

        PieceColor nextTurn = this.turn.getOpposite();

        return new Game(
                this.id,
                this.whitePlayer,
                this.blackPlayer,
                this.state,
                newMoves,
                updatedBoard,
                nextTurn,
                this.result
        );
    }

    public Game finish(String finalResult) {
        if (this.state == GameState.FINISHED) {
            throw new IllegalStateException("The game is already finished.");
        }
        return new Game(
                this.id,
                this.whitePlayer,
                this.blackPlayer,
                GameState.FINISHED,
                this.moves,
                this.currentBoard,
                this.turn,
                finalResult
        );
    }

    public Optional<User> getBlackPlayer() {
        return Optional.ofNullable(blackPlayer);
    }

    private void validateTurn(String userId) {
        String expectedUserId = this.turn.isWhite() ? this.whitePlayer.id() : this.blackPlayer.id();
        if (!userId.equals(expectedUserId)) {
            throw new IllegalArgumentException("It is not user " + userId + "'s turn.");
        }
    }
}
