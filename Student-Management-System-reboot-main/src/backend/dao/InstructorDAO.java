package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Instructor;

public class InstructorDAO {
    public void addInstructor(String instructor_name, String instructor_email) {
        String sql = "insert into instructors (instructor_name, instructor_email) values(?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, instructor_name);
            ps.setString(2, instructor_email);

            ps.executeUpdate();
            System.out.println("Instructor added!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Instructor is already existed");
            // e.printStackTrace();
        }
    }

    public List<Instructor> viewInstructor() {
        List<Instructor> instructors = new ArrayList<>();
        String sql = "select * from instructors";

        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("instructor_id");
                String name = resultSet.getString("instructor_name");
                String email = resultSet.getString("instructor_email");

                Instructor instructor = new Instructor(
                        id, name, email);
                instructors.add(instructor);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Theres an error with the instructor list!");
            // e.printStackTrace();
        }
        return instructors;
    }

    public void assignInstructorToCourse(int course_id, int instructor_id) {
        String sql = "insert into instructors_courses (course_id, instructor_id) values (?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, course_id);
            ps.setInt(2, instructor_id);

            ps.executeUpdate();
            System.out.println("Instructor assigned!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Instructor already assigned to this course!");
            // e.printStackTrace();
        }
    }

    public int getInstructorID(String instructor_name) {
        int id = -1;
        String sql = "select instructor_id from instructors where instructor_name = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, instructor_name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("instructor_id");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Instructor doesnt exist!");
            // e.printStackTrace();
        }
        return id;
    }

    public void removeAssignment(int course_id, int instructor_id) {
        String sql = "delete from instructors_courses where course_id = ? and instructor_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, course_id);
            ps.setInt(2, instructor_id);

            ps.executeUpdate();
            System.out.println("Instructor assignment for the course has been removed!");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
