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

public class Database {

    public Database() {
    }

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
