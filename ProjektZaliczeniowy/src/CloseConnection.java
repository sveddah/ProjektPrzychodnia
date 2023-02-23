import java.sql.*;
public class CloseConnection {
    private Connection connection;

    public CloseConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}