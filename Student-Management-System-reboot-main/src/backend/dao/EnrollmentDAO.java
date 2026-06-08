package backend.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Enrollment;

public class EnrollmentDAO {
    public void enrollStudent(int student_id, int course_id) {
        String sql = "insert into enrollment (student_id, course_id) values(?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, student_id);
            ps.setInt(2, course_id);

            ps.executeUpdate();
            System.out.println("Enrollment added!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Enrollment already existed");
            // e.printStackTrace();
        }
    }

    public List<Enrollment> viewEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "select s.student_name, c.course_name, e.grade\r\n" + //
                "from students as s\r\n" + //
                "left join enrollment  as e on s.student_id = e.student_id\r\n" + //
                "left join courses as c on c.course_id = e.course_id;\r\n";
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String sname = resultSet.getString("student_name");
                String cname = resultSet.getString("course_name");
                String grade = resultSet.getString("grade");
                if (sname == null) {
                    sname = "Unassigned";
                }
                if (cname == null) {
                    cname = "Unassigned";
                }
                if (grade == null) {
                    grade = "Unassigned";
                }

                Enrollment enrollment = new Enrollment(
                        sname, cname, grade);
                enrollments.add(enrollment);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    public void removeEnrollment(int student_id, int course_id) {
        String sql = "delete from enrollment where student_id = ? and course_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, student_id);
            preparedStatement.setInt(2, course_id);

            preparedStatement.executeUpdate();

            System.out.println("Enrollment removed!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Enrollment doesnt exist!");
            // e.printStackTrace();
        }
    }

    public void assignGrade(String grade, int student_id, int course_id) {
        String sql = "update enrollment set grade = ? where student_id = ? and course_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, grade);
            preparedStatement.setInt(2, student_id);
            preparedStatement.setInt(3, course_id);
            preparedStatement.executeUpdate();

            System.out.println("Grade assigned!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("An enrollment needs to be exist in order to assign a grade!");
            // e.printStackTrace();
        }
    }

}
