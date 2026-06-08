package main.java.backend.db;

import java.sql.*;

public class DBConnection {
    private static String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/students_db");
    private static String username = System.getenv().getOrDefault("DB_USER", "root");
    private static String password = System.getenv().getOrDefault("DB_PASSWORD", "16032000No");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}