package bead2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Bendeguz
 */
public class Game4GUI extends JFrame {

    private final JFrame frame;
    private final Player playerOne, playerTwo;
    private BoardGUI bGUI;
    private final Game4GUI g4GUI;

    public Game4GUI() {
        g4GUI = this;

        frame = new JFrame("4-es játék");

        playerOne = new Player("Hugh", new Color(255, 145, 145));
        playerTwo = new Player("Jass", new Color(145, 145, 255));

        bGUI = new BoardGUI(3, playerOne, playerTwo, g4GUI);
        frame.add(bGUI.getPanel());

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game Menu");
        menuBar.add(gameMenu);

        JMenu newMenu = new JMenu("New Game");
        gameMenu.add(newMenu);

        int[] boardSizes = new int[]{3, 5, 7};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newGame(boardSize);
                }
            });
        }

        gameMenu.add(new JSeparator());

        JMenuItem exitMenuItem = new JMenuItem("Exit Game");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void newGame(int boardSize) {
        playerOne.reset();
        playerTwo.reset();
        frame.getContentPane().remove(bGUI.getPanel());
        bGUI = new BoardGUI(boardSize, playerOne, playerTwo, g4GUI);
        frame.getContentPane().add(bGUI.getPanel());
        frame.pack();
    }
}
