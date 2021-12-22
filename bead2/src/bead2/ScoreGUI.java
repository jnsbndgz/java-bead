package bead2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Bendeguz
 */
public class ScoreGUI {

    private final Player playerOne, playerTwo;
    private final JPanel scorePanel;
    private final JLabel scoreOne, scoreTwo;

    public ScoreGUI(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(0, 2));

        scoreOne = new JLabel(playerOne.getName() + "'s score: " + playerOne.getScore(), SwingConstants.CENTER);
        scoreOne.setForeground(Color.WHITE);
        scoreOne.setBackground(playerOne.getColor());
        scoreOne.setOpaque(true);

        scoreTwo = new JLabel(playerTwo.getName() + "'s score: " + playerTwo.getScore(), SwingConstants.CENTER);
        scoreTwo.setForeground(Color.WHITE);
        scoreTwo.setBackground(playerTwo.getColor());
        scoreTwo.setOpaque(true);

        scorePanel.add(scoreOne);
        scorePanel.add(scoreTwo);
    }

    public void update() {
        scoreOne.setText(playerOne.getName() + "'s score: " + playerOne.getScore());
        scoreTwo.setText(playerTwo.getName() + "'s score: " + playerTwo.getScore());

    }

    public JPanel getPanel() {
        return scorePanel;
    }
}
