package bead2;

import java.awt.Color;

/**
 *
 * @author Bendeguz
 */
public class Field {

    private int count;
    private Player owner;

    public Field() {
        this.count = 0;
        this.owner = null;
    }

    public void update(Player newOwner) {
        this.owner = newOwner;
        this.count++;
    }

    public boolean check() {
        return count < 4;
    }

    public int getCount() {
        return count;
    }

    public Color getColor() {
        return owner.getColor();
    }
}
