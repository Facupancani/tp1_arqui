package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDB {

    private static final String URI = "jdbc:mysql://localhost:3306/integrador1";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URI, USER, PASSWORD);
        }
        return conn;
    }

}
