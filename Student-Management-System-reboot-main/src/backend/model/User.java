package backend.model;

import backend.security.Role;

public class User {
    private int userID;
    private String email;
    private String password;
    private Role role;

    public User(int userID, String email, String password, Role role) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

}
