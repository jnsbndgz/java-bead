package snakegame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author Bendeguz
 */
public class SnakeGUI {

    private JFrame frame;
    private GameEngine gameArea;

    public SnakeGUI() {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game Menu");
        menuBar.add(gameMenu);

        JMenuItem highScores = new JMenuItem("High Scores");
        gameMenu.add(highScores);

        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScoreWindow(gameArea.getHighScores(), frame);
            }
        });

        JMenuItem newGame = new JMenuItem("New Game");
        gameMenu.add(newGame);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.restart();
            }
        });

        gameMenu.add(new JSeparator());

        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gameArea = new GameEngine(frame);
        gameArea.setPreferredSize(new Dimension(800, 800));

        frame.getContentPane().add(gameArea);

        frame.setPreferredSize(new Dimension(800, 850));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
