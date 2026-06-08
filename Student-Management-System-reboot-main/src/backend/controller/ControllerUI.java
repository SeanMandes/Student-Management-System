package backend.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.sun.net.httpserver.HttpServer;
import backend.model.User;
import backend.security.AuthService;

public class ControllerUI {

    static final Scanner scanner = new Scanner(System.in);
    static final AuthService authService = new AuthService();

    public static void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/students", new StudentApiController());
        //server.createContext("/instructor", new InstructorApiController());
        server.setExecutor(null);
        System.out.println("Server started at http://localhost:8080/students");
        server.start();





        Scanner scanner = new Scanner(System.in);
        // Assuming authService and other dependencies are initialized here

        System.out.println("========================================");
        System.out.println("   WELCOME TO THE ACADEMIC PORTAL       ");
        System.out.println("========================================");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authService.login(email, password);

        if (user == null) {
            System.out.println("\n[!] Invalid credentials. Exiting system.");
            return;
        }

        // Determine role and route to the correct menu loop
        if (authService.isAdmin(user)) {
            handleAdminMenu(scanner);
        } else if (authService.isStudent(user)) {
            handleStudentMenu(scanner);
        } else {
            System.out.println("\n[!] User role not recognized.");
        }

    }

    private static void handleAdminMenu(Scanner scanner) {
        String choice = "";
        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("\n--- ADMIN DASHBOARD ---");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Add Instructor");
            System.out.println("4. Assign Instructor");
            System.out.println("5. Manage Students");
            System.out.println("6. Assign Grade");
            System.out.println("X. Exit");
            System.out.print("Select an option: ");

            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "1" -> System.out.println("\n[Action] Adding Course..."); // Replace with actual method call
                case "2" -> System.out.println("\n[Action] Removing Course...");
                case "3" -> System.out.println("\n[Action] Adding Instructor...");
                case "4" -> System.out.println("\n[Action] Assigning Instructor...");
                case "5" -> System.out.println("\n[Action] Managing Students...");
                case "6" -> System.out.println("\n[Action] Assigning Grade...");
                case "X" -> System.out.println("\nLogging out of Admin session. Goodbye!");
                default  -> System.out.println("\n[!] Invalid choice. Please try again.");
            }
        }
    }
    private static void handleStudentMenu(Scanner scanner) {
        String choice = "";
        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("\n--- STUDENT PORTAL ---");
            System.out.println("1. View Courses");
            System.out.println("2. View Instructors");
            System.out.println("3. Enroll In Course");
            System.out.println("4. View Grades");
            System.out.println("X. Exit");
            System.out.print("Select an option: ");

            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "1" -> System.out.println("\n[Action] Displaying Courses...");
                case "2" -> System.out.println("\n[Action] Displaying Instructors...");
                case "3" -> System.out.println("\n[Action] Enrolling in Course...");
                case "4" -> System.out.println("\n[Action] Displaying Grades...");
                case "X" -> System.out.println("\nLogging out of Student session. Goodbye!");
                default  -> System.out.println("\n[!] Invalid choice. Please try again.");
            }
        }
    }
}
