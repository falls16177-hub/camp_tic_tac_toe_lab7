package ttt;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements BoardPanel.StatusListener {
    private final JLabel statusLabel = new JLabel(" ");
    private final TTTGame game = new TTTGame();
    private final BoardPanel boardPanel = new BoardPanel(game, this);

    public MainFrame() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(6, 6));
        setJMenuBar(createMenuBar());
        add(boardPanel, BorderLayout.CENTER);
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        statusBar.add(statusLabel, BorderLayout.WEST);
        add(statusBar, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JMenuBar createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.addActionListener(e -> boardPanel.resetBoard());
        resetItem.addActionListener(e -> boardPanel.resetBoard());
        exitItem.addActionListener(e -> dispose());

        gameMenu.add(newItem);
        gameMenu.add(resetItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        mb.add(gameMenu);
        return mb;
    }

    @Override
    public void onStatusText(String text) {
        statusLabel.setText(text);
    }
}
