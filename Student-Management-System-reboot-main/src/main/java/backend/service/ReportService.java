package main.java.backend.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.backend.db.DBConnection;

public class ReportService {
    public void fullReport() {
        String sql = "select s.student_name, c.course_name, e.grade\r\n" + //
                "from students as s\r\n" + //
                "left join enrollment  as e on s.student_id = e.student_id\r\n" + //
                "left join courses as c on c.course_id = e.course_id";

        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.printf("%-30s %18s %40s%n", "Course", "Students", "Grade");
            System.out
                    .println(
                            "------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                String s_name = resultSet.getString("student_name");
                String c_name = resultSet.getString("course_name");
                String grade = resultSet.getString("grade");
                System.out.printf("%-30s %18s %40s%n", s_name, c_name, grade);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void numberOfStudentForEachCourse() {
        String sql = "SELECT c.course_name, COUNT(e.student_id)\r\n" + //
                "FROM courses c\r\n" + //
                "LEFT JOIN enrollment e ON c.course_id = e.course_id\r\n" + //
                "GROUP BY c.course_name";
        try {
            Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.printf("%-30s %10s%n", "Course", "Students");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                String course_name = rs.getString("course_name");
                int numberOfStudent = rs.getInt("COUNT(e.student_id)");
                System.out.printf("%-30s %10s%n", course_name, numberOfStudent);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
