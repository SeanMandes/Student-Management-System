package main.java.backend.model;

public class Instructor {
    private int id;
    private String instructor_email;
    private String instructor_name;

    public Instructor(int id, String instructor_name, String instructor_email) {
        this.id = id;
        this.instructor_email = instructor_email;
        this.instructor_name = instructor_name;
    }

    public Instructor(String instructor_name, String instructor_email) {
        this.instructor_email = instructor_email;
        this.instructor_name = instructor_name;
    }

    public int getId() {
        return id;
    }

    public String getInstructorName() {
        return instructor_name;
    }

    public String getInstructorEmail() {
        return instructor_email;
    }

}
