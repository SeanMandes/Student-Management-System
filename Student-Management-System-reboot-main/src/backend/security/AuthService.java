package backend.security;

import backend.dao.UserDAO;
import backend.model.User;

public class AuthService {

    private static final UserDAO userDAO = new UserDAO();

    public User login(String email, String password) {
        boolean check = userDAO.validateCredentials(email, password);
        if (check) {
            return userDAO.findUserByEmail(email);
        }
        System.out.println("Invalid email or password.");
        return null;
    }

    public boolean isAdmin(User user) {
        if (user == null) {
            return false;
        }
        return user.getRole().equals(Role.ADMIN);
    }

    public boolean isStudent(User user) {
        if (user == null) {
            return false;
        }
        return user.getRole().equals(Role.STUDENT);
    }

}