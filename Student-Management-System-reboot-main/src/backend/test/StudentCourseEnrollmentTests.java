package backend.test;

import backend.dao.CourseDAO;
import backend.dao.EnrollmentDAO;
import backend.dao.InstructorDAO;
import backend.dao.StudentDAO;
import backend.dao.UserDAO;
import backend.security.AuthService;
import backend.service.ReportService;

public class StudentCourseEnrollmentTests {
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final CourseDAO courseDAO = new CourseDAO();
    private static final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private static final InstructorDAO instructorDAO = new InstructorDAO();
    private static final ReportService reportService = new ReportService();
    private static final AuthService authService = new AuthService();
    private static final UserDAO userDAO = new UserDAO();

    public static void runTests() {
        // =========================
        // STUDENT TESTS
        // =========================

        // studentDAO.addStudent(new Student("John", "john@gmail.com", 21));

//        List<Student> students = studentDAO.getAllStudents();
//
//        System.out.println("========== STUDENTS ==========");
//
//        for (Student student : students) {
//            System.out.println(student.getName());
//        }

        // studentDAO.deleteStudent(9);

        // studentDAO.updateStudent(1,
        // "CharlieBruh",
        // "charliebruh@gmail.com",
        // 20);

        // =========================
        // COURSE TESTS
        // =========================

        /*
         * courseDAO.addCourse(new Course(1, "Bio Chemistry"));
         * 
         * List<Course> courses = courseDAO.getAllCourses();
         * 
         * System.out.println("\n========== COURSES ==========");
         * 
         * for (Course course : courses) {
         * System.out.println(course.getCourseName());
         * }
         * 
         * // courseDAO.deleteCourse(1);
         * 
         * // courseDAO.updateCourse(4, "Art");
         */

        // =========================
        // ENROLLMENT VIEW TEST
        // =========================

        /*
         * List<Enrollment> enrollments = enrollmentDAO.viewEnrollments();
         * 
         * System.out.println("\n========== ENROLLMENTS ==========");
         * 
         * for (Enrollment enrollment : enrollments) {
         * 
         * System.out.println(
         * "COURSE NAME: " + enrollment.getCourseName() + "\n" +
         * "STUDENT NAME: " + enrollment.getStudentName() + "\n" +
         * "GRADE: " + enrollment.getGrade() + "\n" +
         * "---------------");
         * }
         */

        /*
         * // =========================
         * // GRADE ASSIGNMENT TEST
         * // =========================
         * 
         * 
         * 
         * int s1 = studentDAO.getStudentID("charlie@gmail.com");
         * int c1 = courseDAO.getCourseID("Operating Systems");
         * 
         * enrollmentDAO.assignGrade("B-", s1, c1);
         * 
         * // =========================
         * // ENROLLMENT TEST CASES
         * // =========================
         * 
         * // -------- Test Case 1 --------
         * 
         * int student1 = studentDAO.getStudentID("charlie@gmail.com");
         * int course1 = courseDAO.getCourseID("History");
         * 
         * enrollmentDAO.enrollStudent(student1, course1);
         * enrollmentDAO.assignGrade("D-", student1, course1);
         * 
         * // -------- Test Case 2 --------
         * 
         * int student2 = studentDAO.getStudentID("emma@gmail.com");
         * int course2 = courseDAO.getCourseID("Physics");
         * 
         * enrollmentDAO.enrollStudent(student2, course2);
         * enrollmentDAO.assignGrade("A", student2, course2);
         * 
         * // -------- Test Case 3 --------
         * 
         * int student3 = studentDAO.getStudentID("ian@gmail.com");
         * int course3 = courseDAO.getCourseID("Database Systems");
         * 
         * enrollmentDAO.enrollStudent(student3, course3);
         * enrollmentDAO.assignGrade("B+", student3, course3);
         * 
         * // -------- Test Case 4 --------
         * 
         * int student4 = studentDAO.getStudentID("hannah@gmail.com");
         * int course4 = courseDAO.getCourseID("English");
         * 
         * enrollmentDAO.enrollStudent(student4, course4);
         * enrollmentDAO.assignGrade("A-", student4, course4);
         * 
         * // -------- Test Case 5 --------
         * 
         * int student5 = studentDAO.getStudentID("david@gmail.com");
         * int course5 = courseDAO.getCourseID("Chemistry");
         * 
         * enrollmentDAO.enrollStudent(student5, course5);
         * enrollmentDAO.assignGrade("C", student5, course5);
         * 
         * // -------- Test Case 6 --------
         * 
         * int student6 = studentDAO.getStudentID("fiona@gmail.com");
         * int course6 = courseDAO.getCourseID("Biology");
         * 
         * enrollmentDAO.enrollStudent(student6, course6);
         * enrollmentDAO.assignGrade("B", student6, course6);
         * 
         * // =========================
         * // REMOVE ENROLLMENT TEST
         * // =========================
         * 
         * enrollmentDAO.removeEnrollment(student1, course1);
         * 
         */

        /*
         * List<Instructor> instructors = instructorDAO.viewInstructor();
         * for (Instructor instructor : instructors) {
         * System.out.println("Instructor ID: " + instructor.getId() + "\n" +
         * "Instructor Name: " + instructor.getInstructorName() + "\n" +
         * "Instructor " + instructor.getInstructorEmail() + "\n" +
         * "--------------------\"---------------");
         * }
         * 
         * 
         * int course_id = courseDAO.getCourseID("Physics");
         * int instructor_id = instructorDAO.getInstructorID("Dr. Brown");
         * 
         * instructorDAO.addInstructor("Dr. Charles", "charles@gmail.com");
         * 
         * instructorDAO.removeAssignment(course_id, instructor_id);
         */

        // reportService.numberOfStudentForEachCourse();
        // reportService.fullReport();

        // String email = "johnpork@gmail.com";

        // userDAO.findUserByEmail(email);

        // userDAO.createUser(new User("brown@university.edu", "123456789",
        // Role.ADMIN));

        // System.out.println(userDAO.validateCredentials("johnpork@gmail.com",
        // "12345"));

        System.out.println("\n========== ALL TESTS COMPLETED ==========");
    }

}