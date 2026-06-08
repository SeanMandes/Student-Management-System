package backend.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import backend.db.DBConnection;
import backend.model.User;
import backend.security.Role;

public class UserDAO {

    public User findUserByEmail(String user_email) {
        User user_data = null;
        String sql = "SELECT * FROM user WHERE user_email = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user_email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userID");
                String email = rs.getString("user_email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                user_data = new User(id, email, password, Role.valueOf(role));

//                System.out.println(
//                        "USER ID: " + id + "\n" +
//                                "USER EMAIL: " + email + "\n" +
//                                "USER PASSWORD: " + password + "\n" +
//                                "USER ROLE: " + role);
            }
//            else {
//                System.out.println("User doesn't exist!");
//            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_data;
    }

    public boolean validateCredentials(String email, String password) {
        User user_data = findUserByEmail(email);

        if (user_data == null) {
//            System.out.println("User is incorrect!");
            return false;
        }

        if (user_data.getPassword().equals(password)) {
            return true;
        }

//        System.out.println("Wrong password");
        return false;
    }

    public void createUser(User user) {
        String sql = "insert into user (user_email, password, role) values (?,?,?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
// after done with the function calls, try and wrap it up with the API calls.
// Test it, see how it runs
// import Spring Boot