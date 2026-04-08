package ttt;

import java.util.Arrays;
import java.util.Optional;

/**
 * Model: immutable-size 3x3 Tic Tac Toe board that holds marks 'X'/'O'.
 */
public class TTTBoard {
    public static final int SIZE = 3;

    private final char[][] cells = new char[SIZE][SIZE]; // '\0' means empty

    public TTTBoard() {
        reset();
    }

    public void reset() {
        for (int r = 0; r < SIZE; r++) {
            Arrays.fill(cells[r], '\0');
        }
    }

    public boolean isEmpty(int row, int col) {
        validate(row, col);
        return cells[row][col] == '\0';
    }

    public Optional<Character> get(int row, int col) {
        validate(row, col);
        char m = cells[row][col];
        return m == '\0' ? Optional.empty() : Optional.of(m);
    }

    /**
     * Places a mark on an empty cell.
     * @return true if placement succeeded; false if cell already occupied
     */
    public boolean place(int row, int col, char mark) {
        validate(row, col);
        if (cells[row][col] != '\0') return false;
        cells[row][col] = mark;
        return true;
    }

    public boolean isFull() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (cells[r][c] == '\0') return false;
            }
        }
        return true;
    }

    public Optional<Character> winner() {
        // Rows
        for (int r = 0; r < SIZE; r++) {
            char p = cells[r][0];
            if (p != '\0' && p == cells[r][1] && p == cells[r][2]) return Optional.of(p);
        }
        // Cols
        for (int c = 0; c < SIZE; c++) {
            char p = cells[0][c];
            if (p != '\0' && p == cells[1][c] && p == cells[2][c]) return Optional.of(p);
        }
        // Diagonals
        char p = cells[0][0];
        if (p != '\0' && p == cells[1][1] && p == cells[2][2]) return Optional.of(p);
        p = cells[0][2];
        if (p != '\0' && p == cells[1][1] && p == cells[2][0]) return Optional.of(p);
        return Optional.empty();
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("row/col out of bounds: (" + row + "," + col + ")");
        }
    }
}
