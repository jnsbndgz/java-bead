package snakegame;

import javax.swing.ImageIcon;

/**
 *
 * @author Bendeguz
 */
public class Obstacle extends Sprite {

    public Obstacle(int x, int y) {
        super(x, y, 40, 40, new ImageIcon("data/images/obstacle.jpg").getImage());
    }

}
