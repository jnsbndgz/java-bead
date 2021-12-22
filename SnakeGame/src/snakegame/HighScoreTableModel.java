package snakegame;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bendeguz
 */
public class HighScoreTableModel extends AbstractTableModel {

    private final ArrayList<HighScore> highScores;
    private final String[] colName = new String[]{"Player Name", "Score"};

    public HighScoreTableModel(ArrayList<HighScore> highScores) {
        this.highScores = highScores;
    }

    @Override
    public int getRowCount() {
        return highScores.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int r, int c) {
        HighScore h = highScores.get(r);
        if (c == 0) {
            return h.getName();
        } else if (c == 1) {
            return h.getScore();
        }
        return 0;
    }

    @Override
    public String getColumnName(int i) {
        return colName[i];
    }

}
