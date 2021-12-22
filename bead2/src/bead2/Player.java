package bead2;

import java.awt.Color;

/**
 *
 * @author Bendeguz
 */
public class Player {

    private final String pName;
    private final Color pColor;
    private int score;

    public Player(String pName, Color pColor) {
        this.pName = pName;
        this.pColor = pColor;
    }

    public void increaseScore() {
        score++;
    }

    public String getName() {
        return pName;
    }

    public Color getColor() {
        return pColor;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }
}
