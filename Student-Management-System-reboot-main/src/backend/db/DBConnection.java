package backend.db;

import java.sql.*;

public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/students_db";
    private static String username = "root";
    private static String password = "16032000No";

    /*
     * public void fetchStudents() {
     * // String sql = "select student_name from students where student_id = 3";
     * String sql = "select student_name from students";
     * 
     * Connection connection = null;
     * Statement statement = null;
     * ResultSet resultSet = null;
     * 
     * try {
     * Class.forName("com.mysql.cj.jdbc.Driver");
     * connection = DriverManager.getConnection(url, username, password);
     * System.out.println("Connection successful!");
     * 
     * statement = connection.createStatement();
     * resultSet = statement.executeQuery(sql);
     * 
     * resultSet.next();
     * String name = resultSet.getString(1);
     * System.out.println(name);
     * 
     * 
     * while (resultSet.next()) {
     * String name = resultSet.getString(1);
     * System.out.println(name);
     * }
     * 
     * } catch (ClassNotFoundException e) {
     * System.out.println("JDBC Driver not found.");
     * e.printStackTrace();
     * } catch (SQLException e) {
     * System.out.println("Failed to connect to the database.");
     * e.printStackTrace();
     * } finally {
     * try {
     * if (connection != null) {
     * statement.close();
     * connection.close();
     * System.out.println("Connection closed.");
     * }
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     */

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
