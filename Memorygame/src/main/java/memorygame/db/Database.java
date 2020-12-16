package memorygame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import memorygame.ui.MemorygameUi;

/**
 * Database class takes care of saving data to Scores.mv database.
 */
public class Database {

    /**
     * This method is called in MemorygameUi and it creates a Database object.
     */
    public Database() {
    }

    /**
     * This method is called in MemorygameUi and it creates a database called
     * Score if it doesn't already exist.
     */
    public void createDatabase() {
        try {
            Connection conn = createConnection();
            PreparedStatement statement
                    = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Score"
                            + " (id INTEGER AUTO_INCREMENT PRIMARY KEY,"
                            + " seconds INTEGER);");
            statement.execute();
            statement.close();
            conn.close();
            statement = null;
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called in Controller and it saves player's timescore to
     * database Score.
     *
     * @param score is a value which represents timescore, aka how long it took
     * in seconds for a player to win the game.
     * @throws SQLException provides information on database access error or
     * other errors.
     */
    public void saveScoreToDatabase(int score) throws SQLException {
        Connection conn = createConnection();
        PreparedStatement statement
                = conn.prepareStatement("INSERT INTO Score (seconds) "
                        + "VALUES (?)");
        statement.setInt(1, score);
        statement.executeUpdate();
        statement.close();
        conn.close();

        statement = null;
        conn = null;
    }

    /**
     * This method is called in MemorygameUi and it displays top 3 timescores
     * from database Score inside a VBox.
     *
     * @param scores is a graphic component where top 3 timescores from database
     * Score are set to display
     */
    public void getScoresFromDatabase(VBox scores) {
        int i = 1;
        try {
            Connection conn = createConnection();
            PreparedStatement statement
                    = conn.prepareStatement("SELECT TOP 3"
                            + " seconds FROM Score ORDER BY seconds");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int time = rs.getInt("seconds");
                Text score = new Text(String.valueOf(i) + ". "
                        + String.valueOf(time) + " sekuntia");
                scores.getChildren().add(score);
                i++;
            }
            statement.close();
            conn.close();
            statement = null;
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(MemorygameUi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method creates a connection to database Score.
     *
     * @return Connection object
     */
    public Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:./scores",
                    "sa", "");
            return connection;
        } catch (SQLException e) {
            return null;
        }
    }
}
