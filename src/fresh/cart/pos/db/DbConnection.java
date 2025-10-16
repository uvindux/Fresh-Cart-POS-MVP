package fresh.cart.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection; // singleton instance
    private Connection connection; // connection object

    // private constructor to prevent external instantiation
    private DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/thogakade";
            String username = "root";
            String password = ""; // leave empty if no password in XAMPP
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database Connected Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // public method to return the single instance
    public static DbConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    // getter for the connection
    public Connection getConnection() {
        return connection;
    }
}
