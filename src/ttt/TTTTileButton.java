package ttt;

import javax.swing.JButton;
import java.awt.Font;

/**
 * View: A JButton that knows its row/col position and can render a mark 'X'/'O'.
 */
public class TTTTileButton extends JButton {
    private final int row;
    private final int col;

    public TTTTileButton(int row, int col) {
        super("");
        this.row = row;
        this.col = col;
        setFocusPainted(false);
        setFont(getFont().deriveFont(Font.BOLD, 36f));
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public void setMark(char mark) {
        setText(mark == '\0' ? "" : String.valueOf(mark));
    }

    public void resetTile() {
        setEnabled(true);
        setText("");
    }
}
