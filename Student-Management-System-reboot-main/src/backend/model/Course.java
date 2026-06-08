package backend.model;

public class Course {
    private String course_name;
    private int id;

    public Course(String course_name) {
        this.course_name = course_name;
    }

    public Course(int id, String course_name) {
        this.id = id;
        this.course_name = course_name;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return course_name;
    }

}
