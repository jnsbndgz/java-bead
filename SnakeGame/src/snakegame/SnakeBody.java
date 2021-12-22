package snakegame;

import javax.swing.ImageIcon;

/**
 *
 * @author Bendeguz
 */
public class SnakeBody extends Sprite {

    public SnakeBody(int x, int y) {
        super(x, y, 40, 40, new ImageIcon("data/images/snakeBody.jpg").getImage());
    }
}
