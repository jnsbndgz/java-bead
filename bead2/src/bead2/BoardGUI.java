package bead2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Bendeguz
 */
public class BoardGUI {

    private final JButton[][] buttons;
    private final JPanel panel, scorePanel, boardPanel;
    private final Board board;
    private final Game4GUI g4GUI;

    public BoardGUI(int boardSize, Player playerOne, Player playerTwo, Game4GUI g4GUI) {
        board = new Board(boardSize, playerOne, playerTwo);

        this.g4GUI = g4GUI;

        scorePanel = board.getScorePanel();

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        boardPanel.setPreferredSize(new Dimension(board.getBoardSize() * 75, board.getBoardSize() * 75));

        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = new JButton();
                button.setBackground(new Color(255, 255, 255));
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(75, 75));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scorePanel, BorderLayout.NORTH);
        panel.add(boardPanel, BorderLayout.SOUTH);
    }

    private void refresh(int x, int y) {
        Field field = board.get(x, y);
        JButton button = buttons[x][y];
        button.setBackground(field.getColor());
        button.setText(String.valueOf(field.getCount()));
    }

    public JPanel getPanel() {
        return panel;
    }

    class ButtonListener implements ActionListener {

        private final int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            board.clicked(x, y);

            refresh(x, y);
            if (x - 1 >= 0) {
                refresh(x - 1, y);
            }
            if (y - 1 >= 0) {
                refresh(x, y - 1);
            }
            if (x + 1 < board.getBoardSize()) {
                refresh(x + 1, y);
            }
            if (y + 1 < board.getBoardSize()) {
                refresh(x, y + 1);
            }

            if (board.isOver()) {
                JOptionPane.showMessageDialog(boardPanel, "The winner is " + board.getWinner().getName() + " with the score of " + board.getWinner().getScore() + " points!", "Game over", JOptionPane.PLAIN_MESSAGE);
                g4GUI.newGame(board.getBoardSize());
            }
        }
    }
}
