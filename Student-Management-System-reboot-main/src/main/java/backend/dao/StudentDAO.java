package main.java.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.backend.db.DBConnection;
import main.java.backend.model.Student;

public class StudentDAO {
    public void addStudent(Student student) {
        String sql = "insert into students(student_name, student_email, age) values (?, ?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());

            ps.executeUpdate();
            System.out.println("Student added!");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Student already exist");
            // e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from students";
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("student_email"),
                        resultSet.getInt("age"));
                students.add(student);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void updateStudent(int id, String student_name, String student_email, int age) {
        String sql = "update students set student_name = ?, student_email = ?, age = ? where student_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, student_name);
            ps.setString(2, student_email);
            ps.setInt(3, age);
            ps.setInt(4, id);

            ps.executeUpdate();
            System.out.println("Student updated!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "delete from students where student_id = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Student removed!");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStudentID(String student_email) {
        int id = -1;
        String sql = "select student_id from students where student_email = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student_email);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("student_id");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean studentExist(int ID){
        String sql = "select * from students where student_id = ?";
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String email = resultSet.getString("student_email");
            connection.close();
        } catch (SQLException e){
//            e.printStackTrace();
            return false;
        }

        return true;
    }

}
