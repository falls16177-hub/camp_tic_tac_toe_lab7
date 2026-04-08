package ttt;

import java.util.Optional;

/**
 * Controller/model-composite that manages turn order and simple game flow.
 */
public class TTTGame {
    private final TTTBoard board;
    private char current;

    public TTTGame() {
        this.board = new TTTBoard();
        this.current = 'X';
    }

    public TTTBoard board() {
        return board;
    }

    public char currentPlayer() {
        return current;
    }

    public boolean inProgress() {
        return winner().isEmpty() && !board.isFull();
    }

    public boolean applyMove(int row, int col) {
        if (!inProgress()) return false;
        boolean placed = board.place(row, col, current);
        if (placed && inProgress()) {
            current = other(current);
        }
        return placed;
    }

    public Optional<Character> winner() {
        return board.winner();
    }

    public void reset() {
        board.reset();
        current = 'X';
    }

    private char other(char p) {
        return p == 'X' ? 'O' : 'X';
    }
}
