package ttt;

public class TTTBoardTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testEmptyBoard();
        testRowWin();
        testColWin();
        testDiagWin();
        testDraw();
        System.out.printf("Tests passed: %d, failed: %d%n", passed, failed);
        if (failed > 0) System.exit(1);
    }

    private static void assertTrue(boolean cond, String msg) {
        if (!cond) {
            failed++;
            System.out.println("[FAIL] " + msg);
        } else {
            passed++;
        }
    }

    private static void testEmptyBoard() {
        TTTBoard b = new TTTBoard();
        assertTrue(b.winner().isEmpty(), "No winner on empty board");
        assertTrue(!b.isFull(), "Empty board not full");
    }

    private static void testRowWin() {
        TTTBoard b = new TTTBoard();
        b.place(1,0, 'X');
        b.place(1,1, 'X');
        b.place(1,2, 'X');
        assertTrue(b.winner().orElse('\0') == 'X', "Row win for X");
    }

    private static void testColWin() {
        TTTBoard b = new TTTBoard();
        b.place(0,2, 'O');
        b.place(1,2, 'O');
        b.place(2,2, 'O');
        assertTrue(b.winner().orElse('\0') == 'O', "Col win for O");
    }

    private static void testDiagWin() {
        TTTBoard b = new TTTBoard();
        b.place(0,0, 'X');
        b.place(1,1, 'X');
        b.place(2,2, 'X');
        assertTrue(b.winner().orElse('\0') == 'X', "Diag win for X");
    }

    private static void testDraw() {
        TTTBoard b = new TTTBoard();
        // X O X
        // X O O
        // O X X
        b.place(0,0, 'X');
        b.place(0,1, 'O');
        b.place(0,2, 'X');
        b.place(1,0, 'X');
        b.place(1,1, 'O');
        b.place(1,2, 'O');
        b.place(2,0, 'O');
        b.place(2,1, 'X');
        b.place(2,2, 'X');
        assertTrue(b.winner().isEmpty(), "No winner in draw");
        assertTrue(b.isFull(), "Board should be full in draw");
    }
}
