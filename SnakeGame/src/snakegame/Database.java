package snakegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bendeguz
 */
public class Database {

    private final String tableName = "scores";
    private final Connection conn;
    private ArrayList<HighScore> hs;

    public Database() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/snake?"
                    + "serverTimezone=UTC&user=user&password=user");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.conn = c;
        hs = new ArrayList<>();
        loadHighScores();
    }

    public void loadHighScores() {
        hs.clear();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT player, score FROM " + tableName + " ORDER BY score DESC LIMIT 10");
            while (rs.next()) {
                String playerName = rs.getString("player");
                int score = rs.getInt("score");
                hs.add(new HighScore(playerName, score));
            }
        } catch (Exception e) {
            System.out.println("loadHighScores error: " + e.getMessage());
        }
    }

    public ArrayList<HighScore> getHighScores() {
        return hs;
    }

    public int storeToDatabase(String playerName, int score) {

        for (HighScore his : hs) {
            if (his.getName().equals(playerName) || his.getScore() == score) {
                return 0;
            }
        }

        if(playerName.equals("")) {
            playerName = "Anonymous";
        }
        
        try (Statement stmt = conn.createStatement()) {
            String s = "INSERT INTO " + tableName
                    + " (player, score) "
                    + "VALUES ('" + playerName + "', " + score + ")";
            return stmt.executeUpdate(s);
        } catch (Exception e) {
            System.out.println("storeToDatabase error");
        }

        loadHighScores();

        return 0;
    }
}
