package snakegame;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Bendeguz
 */
public class SaveScoreDialog extends OkCancelDialog {

    private JTextField edit;
    private Database db;
    private int score;
    private JLabel jl = new JLabel("Please enter your name:");

    public SaveScoreDialog(JFrame parent, String title, Database db, int score) {
        super(parent, title);
        this.db = db;
        this.score = score;
        edit = new JTextField();
        setLayout(new BorderLayout());
        add("North", jl);
        add("Center", edit);
        add("South", btnPanel);
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    public String getValue() {
        return edit.getText();
    }

    @Override
    protected boolean processOK() {
        db.storeToDatabase(edit.getText(), score);
        return true;
    }

    @Override
    protected void processCancel() {
    }
}
