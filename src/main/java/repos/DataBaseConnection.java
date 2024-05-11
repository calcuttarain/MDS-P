package repos;

import java.sql.*;

class DataBaseConnection {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static Connection connection;

    static {
        URL = "jdbc:mysql://localhost:3306/db_mds";
        USER = "mds_user";
        PASSWORD = "root";
    }

    public static Connection getConnection() throws SQLException
    {
        if(connection == null || connection.isClosed())
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        return connection;
    }

    public static void stopConnection() throws SQLException
    {
        if(!(connection == null || connection.isClosed()))
            connection.close();
    }
}
