package ttt;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

/**
 * View + minimal controller glue for the 3x3 grid of buttons.
 */
public class BoardPanel extends JPanel {
    private final TTTGame game;
    private final TTTTileButton[][] buttons = new TTTTileButton[TTTBoard.SIZE][TTTBoard.SIZE];
    private final StatusListener statusListener;

    public interface StatusListener {
        void onStatusText(String text);
    }

    public BoardPanel(TTTGame game, StatusListener statusListener) {
        super(new GridLayout(TTTBoard.SIZE, TTTBoard.SIZE, 4, 4));
        this.game = game;
        this.statusListener = statusListener;
        initButtons();
        updateStatusText();
    }

    private void initButtons() {
        for (int r = 0; r < TTTBoard.SIZE; r++) {
            for (int c = 0; c < TTTBoard.SIZE; c++) {
                TTTTileButton b = new TTTTileButton(r, c);
                int rr = r, cc = c;
                b.addActionListener(e -> onTilePressed(rr, cc, b));
                buttons[r][c] = b;
                add(b);
            }
        }
    }

    private void onTilePressed(int row, int col, TTTTileButton button) {
        if (!game.inProgress()) return;
        boolean ok = game.applyMove(row, col);
        if (ok) {
            game.board().get(row, col).ifPresent(button::setMark);
            button.setEnabled(false);
            if (game.winner().isPresent()) {
                char w = game.winner().get();
                showEnd(w + " wins!");
            } else if (game.board().isFull()) {
                showEnd("It's a draw.");
            } else {
                updateStatusText();
            }
        }
    }

    private void updateStatusText() {
        if (statusListener != null) {
            statusListener.onStatusText("Turn: " + game.currentPlayer());
        }
    }

    private void showEnd(String message) {
        if (statusListener != null) statusListener.onStatusText(message);
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetBoard() {
        game.reset();
        for (int r = 0; r < TTTBoard.SIZE; r++) {
            for (int c = 0; c < TTTBoard.SIZE; c++) {
                buttons[r][c].resetTile();
            }
        }
        updateStatusText();
    }
}
