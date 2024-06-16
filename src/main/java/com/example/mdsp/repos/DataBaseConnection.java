package com.example.mdsp.repos;

import java.sql.*;

public class DataBaseConnection {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static Connection connection;

    static {
        URL = "jdbc:mysql://localhost:3306/db_mds";
        USER = "mds_user";
        PASSWORD = "root";
    }

    public static Connection getConnection()
    {
        try {
            if(connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void stopConnection()
    {
        try {
            if(!(connection == null || connection.isClosed()))
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
