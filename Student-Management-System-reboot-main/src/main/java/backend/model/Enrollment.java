package main.java.backend.model;

public class Enrollment {
    private int courseId;
    private int studentId;
    private String grade;
    private String course_name;
    private String student_name;

    public Enrollment(int courseId, int studentId, String grade) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
    }

    public Enrollment(String course_name, String student_name, String grade) {
        this.course_name = course_name;
        this.student_name = student_name;
        this.grade = grade;
    }

    public String getStudentName() {
        return student_name;
    }

    public String getCourseName() {
        return course_name;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getGrade() {
        return grade;
    }

}
