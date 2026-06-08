package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Course;

public class CourseDAO {
    public void addCourse(Course course) {
        String sql = "insert into courses (course_name) values (?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, course.getCourseName());

            ps.executeUpdate();
            System.out.println("Course added!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("You cant add a course twice!");
            // e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "select * from courses";
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name"));
                courses.add(course);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void updateCourse(int course_id, String course_name) {
        String sql = "update courses set course_name = ? where course_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(2, course_id);
            ps.setString(1, course_name);
            ps.executeUpdate();
            System.out.println("Course updated!");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(int id) {
        String deleteEnrollments = "DELETE FROM enrollment WHERE course_id = ?";
        String deleteCourse = "DELETE FROM courses WHERE course_id = ?";

        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement ps1 = connection.prepareStatement(deleteEnrollments);
                    PreparedStatement ps2 = connection.prepareStatement(deleteCourse)) {

                ps1.setInt(1, id);
                ps1.executeUpdate();

                ps2.setInt(1, id);
                int rows = ps2.executeUpdate();

                if (rows == 0) {
                    connection.rollback();
                    System.out.println("Course not found!");
                    return;
                }

                connection.commit();
                System.out.println("Course removed!");

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCourseID(String course_name) {
        int id = -1;
        String sql = "select course_id from courses where course_name = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, course_name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("course_id");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
