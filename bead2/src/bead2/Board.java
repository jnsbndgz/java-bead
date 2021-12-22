package bead2;

import javax.swing.JPanel;

/**
 *
 * @author Bendeguz
 */
public class Board {

    private final int boardSize;
    private final Field[][] fields;
    private boolean isPlayerOne = true;
    private final Player playerOne, playerTwo;
    private final ScoreGUI sGUI;

    public Board(int boardSize, Player playerOne, Player playerTwo) {
        this.boardSize = boardSize;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.sGUI = new ScoreGUI(playerOne, playerTwo);
        fields = new Field[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    public void clicked(int x, int y) {
        if (get(x, y).getCount() < 4) {
            update(x, y);

            if (x - 1 >= 0 && get(x - 1, y).getCount() < 4) {
                update(x - 1, y);
            }

            if (x + 1 < getBoardSize() && get(x + 1, y).getCount() < 4) {
                update(x + 1, y);
            }

            if (y + 1 < getBoardSize() && get(x, y + 1).getCount() < 4) {
                update(x, y + 1);
            }

            if (y - 1 >= 0 && get(x, y - 1).getCount() < 4) {
                update(x, y - 1);
            }

            nextRound();
        }
    }

    public void update(int x, int y) {
        if (isPlayerOne) {
            get(x, y).update(playerOne);
            if (get(x, y).getCount() == 4) {
                playerOne.increaseScore();
            }
        } else {
            get(x, y).update(playerTwo);
            if (get(x, y).getCount() == 4) {
                playerTwo.increaseScore();
            }
        }
    }

    private void nextRound() {
        isPlayerOne = !isPlayerOne;
        sGUI.update();
    }

    public boolean isOver() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (fields[i][j].getCount() < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player getWinner() {
        if (playerOne.getScore() > playerTwo.getScore()) {
            return playerOne;
        }
        return playerTwo;
    }

    public JPanel getScorePanel() {
        return sGUI.getPanel();
    }

    public Field get(int x, int y) {
        return fields[x][y];
    }

    public int getBoardSize() {
        return boardSize;
    }
}
