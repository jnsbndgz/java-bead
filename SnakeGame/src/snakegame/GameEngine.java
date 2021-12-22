package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author Bendeguz
 */
public class GameEngine extends JPanel {

    private final int FPS = 60;
    private final int SNAKE_SPEED = 10;
    private boolean paused = false;
    private boolean gameEnd = false;
    private ArrayList<Obstacle> obs;
    private Image background;
    private SnakeHead snake;
    private Apple apple;
    private Timer newFrameTimer;
    private int appleCount = 0;
    private Database db;
    private JFrame frame;

    public GameEngine(JFrame frame) {
        super();
        this.frame = frame;
        this.setBackground(new Color(194, 178, 128));

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelX(-SNAKE_SPEED);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed right");
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelX(SNAKE_SPEED);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelY(-SNAKE_SPEED);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelY(SNAKE_SPEED);
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "game paused");
        this.getActionMap().put("game paused", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "game paused space");
        this.getActionMap().put("game paused space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (paused) {
                    paused = false;
                }
            }
        });

        db = new Database();

        restart();

        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public void restart() {
        paused = false;
        gameEnd = false;

        this.appleCount = 0;
        this.obs = new ArrayList<>();
        this.snake = new SnakeHead(400, 400);
        this.apple = newApple();

        db.loadHighScores();

        for (int i = 0; i < 10; i++) {
            Obstacle tmpOb;
            boolean ok;

            do {
                ok = true;

                tmpOb = new Obstacle((int) (Math.random() * (700 - 100)) + 100, (int) (Math.random() * (700 - 100)) + 100);

                if (tmpOb.collides(snake) || tmpOb.collides(apple)) {
                    ok = false;
                }

                for (SnakeBody sb : snake.getBody()) {
                    if (tmpOb.collides(sb)) {
                        ok = false;
                    }
                }

                for (Obstacle ob : obs) {
                    if (tmpOb.collides(ob)) {
                        ok = false;
                    }
                }

            } while (!ok);

            obs.add(tmpOb);
        }

    }

    private Apple newApple() {
        Apple tmpApple;
        boolean ok;

        do {
            ok = true;

            tmpApple = new Apple((int) (Math.random() * (700 - 100)) + 100, (int) (Math.random() * (700 - 100)) + 100);

            if (tmpApple.collides(snake)) {
                ok = false;
            }

            for (SnakeBody sb : snake.getBody()) {
                if (tmpApple.collides(sb)) {
                    ok = false;
                }
            }
            if (!obs.isEmpty()) {
                for (Obstacle ob : obs) {
                    if (tmpApple.collides(ob)) {
                        ok = false;
                    }
                }
            }

        } while (!ok);

        return tmpApple;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        snake.draw(grphcs);
        apple.draw(grphcs);
        for (Obstacle ob : obs) {
            ob.draw(grphcs);
        }
        Font font = new Font("Serif", Font.BOLD, 24);
        grphcs.setFont(font);
        grphcs.drawString("Score: " + appleCount, 20, 30);

        if (gameEnd) {
            font = new Font("Serif", Font.BOLD, 32);
            grphcs.setFont(font);
            grphcs.drawString("Game over", 320, 380);
        }

        if (paused && !gameEnd) {
            font = new Font("Serif", Font.BOLD, 32);
            grphcs.setFont(font);
            grphcs.drawString("Game paused", 320, 380);
            grphcs.drawString("Press ESC or SPACE to continue!", 180, 420);
        }
    }

    public ArrayList<HighScore> getHighScores() {
        return db.getHighScores();
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (!paused && !gameEnd) {
                if (apple.collides(snake)) {
                    appleCount++;
                    apple = newApple();
                    snake.grow();
                }

                for (Obstacle ob : obs) {
                    if (snake.collides(ob)) {
                        gameEnd = true;
                    }
                }

                if (snake.collidesBody()) {
                    gameEnd = true;
                }

                if (snake.getX() < 0 || snake.getY() < 0 || snake.getX() > 761 || snake.getY() > 761) {
                    gameEnd = true;
                }

                snake.move();
            }

            repaint();

            if (gameEnd) {
                new SaveScoreDialog(frame, "Save Score", db, appleCount);
                restart();
                snake.move();
                paused = true;
            }
        }

    }
}
