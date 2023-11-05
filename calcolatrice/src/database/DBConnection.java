package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/loginCalcolatrice";
    private static final String dbUser = "root";
    private static final String dbPassword = "";

    private static int userID = -1;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, dbUser, dbPassword);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static void setUserID(int id) {
        userID = id;
    }

    public static int getUserID() {
        return userID;
    }
}
