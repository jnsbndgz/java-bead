package snakegame;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 *
 * @author Bendeguz
 */
public class SnakeHead extends Sprite {

    private int velX = 0;
    private int velY = 0;
    private ArrayList<SnakeBody> body;

    public SnakeHead(int x, int y) {
        super(x, y, 40, 40, new ImageIcon("data/images/snakeHead.jpg").getImage());
        body = new ArrayList<>();
        body.add(new SnakeBody(this.x, this.y + 40));

        int rand = (int) (Math.random() * 4);

        switch (rand) {
            case 0:
                velX = -10;
                break;
            case 1:
                velX = 10;
                break;
            case 2:
                velY = -10;
                break;
            case 3:
                velY = 10;
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);

        for (SnakeBody sb : body) {
            sb.draw(g);
        }
    }

    public void grow() {
        body.add(new SnakeBody(this.x, this.y));
    }

    public void move() {

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setX(body.get(i - 1).getX());
            body.get(i).setY(body.get(i - 1).getY());
        }

        body.get(0).setX(this.x);
        body.get(0).setY(this.y);

        if (velX != 0) {
            this.x += this.velX;
        }
        if (velY != 0) {
            this.y += this.velY;
        }
    }

    public boolean collidesBody() {
        boolean collides = false;

        if (body.size() > 7) {
            for (int i = 6; i < body.size() - 1; i++) {
                if (this.collides(body.get(i))) {
                    collides = true;
                    break;
                }
            }
        }

        return collides;
    }

    public void setVelX(int velX) {
        if (velY != 0) {
            this.velX = velX;
            this.velY = 0;
        }
    }

    public void setVelY(int velY) {
        if (velX != 0) {
            this.velY = velY;
            this.velX = 0;
        }
    }

    public ArrayList<SnakeBody> getBody() {
        return body;
    }

}
