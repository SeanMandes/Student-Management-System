package main.java.backend.model;

public class Instructor_Courses {
    private int instructorId;
    private int courseId;

    public Instructor_Courses(int instructorId, int courseId) {
        this.instructorId = instructorId;
        this.courseId = courseId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public int getCourseId() {
        return courseId;
    }

}
