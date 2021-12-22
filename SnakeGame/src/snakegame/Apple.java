package snakegame;

import javax.swing.ImageIcon;

public class Apple extends Sprite {

    public Apple(int x, int y) {
        super(x, y, 40, 40, new ImageIcon("data/images/apple.jpg").getImage());
    }
}
