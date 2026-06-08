package main.java.backend.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.backend.dao.StudentDAO;
import main.java.backend.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StudentApiController implements HttpHandler {
    private static final StudentDAO studentDAO = new StudentDAO();
    public static List<Student> students = new ArrayList<>();
    public static List<List<String>> students_info = new ArrayList<>();



    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String method = exchange.getRequestMethod();
        System.out.println("THIS IS THE METHOD: " + method);
        switch (method){
            case "GET" -> handleGetStudent(exchange);
            case "POST" -> handlePostStudent(exchange);
            case "PUT" -> handlePutStudent(exchange);
            case "DELETE" -> handleDeleteStudent(exchange);
            default -> sendResponse(exchange, 405, "Method Not Allowed");
        }
    }

    private void handleGetStudent(HttpExchange exchange) throws IOException{
        students = studentDAO.getAllStudents();
        for (Student student : students){
            List<String> info = new ArrayList<>();
            info.add(String.valueOf(student.getId()));
            info.add(student.getName());
            info.add(student.getEmail());
            info.add(String.valueOf(student.getAge()));
            students_info.add(info);
        }
        sendResponse(exchange, 200, String.valueOf(students_info));
    }

    private void handlePostStudent(HttpExchange exchange) throws IOException {
        String body = readRequestBody(exchange);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);

            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();
            int age = jsonNode.get("age").asInt();
            Student new_student = new Student(name, email, age);
            studentDAO.addStudent(new_student);
            sendResponse(exchange, 200, "Student successfully added: " + name);
        } catch (Exception e) {
            sendResponse(exchange, 400, "Invalid student data");
        }
    }

    private void handlePutStudent(HttpExchange exchange) throws IOException {


        String body = readRequestBody(exchange);
        try{
            // Turn JSON in to a Java object-data
            ObjectMapper objectMapper = new ObjectMapper();

            // JsonNode is Java object representation of Json
            // Add Json (java object-data) into JsonNode (java object)
            JsonNode jsonNode = objectMapper.readTree(body);

            int id = jsonNode.get("id").asInt();
            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();
            int age = jsonNode.get("age").asInt();

            boolean exit = studentDAO.studentExist(id);
            if (exit){
                studentDAO.updateStudent(id, name, email,age);
                sendResponse(exchange, 200, "Student successfully changed: " + name);
            } else {
                sendResponse(exchange, 404, "User not found");
            }

        } catch (Exception e){
            sendResponse(exchange, 400, "Invalid JSON structure or missing fields");
        }
    }

    private void handleDeleteStudent(HttpExchange exchange) throws IOException{
        String body = readRequestBody(exchange);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);

            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();

            int index = studentDAO.getStudentID(email);

try{
    studentDAO.deleteStudent(index);
    sendResponse(exchange, 200, "User deleted: " + name);

}catch (IOException e){
    sendResponse(exchange, 404, "User not found");
}
        }catch (NumberFormatException e){
            sendResponse(exchange, 400, "Invalid index");
        }
    }


    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException{
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private String readRequestBody(HttpExchange exchange) throws IOException{
        InputStream is = exchange.getRequestBody();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }


    // CURL (Client URL): A COMMAND-LINE TOOL USED TO TRANSFER DATA TO AND FROM SERVER
    // BRUNO OR POSTMAN CURL WITH AN UI

    // GET (ALL USERS)
    // curl.exe -X GET http://localhost:8080/users

    // POST (ADD NEW USER)
    // curl.exe -X POST -d "Alice" http://localhost:8080/users

    // UPDATE (UPDATE USER)
    // curl.exe -X PUT -d "0:Bob" http://localhost:8080/users

    // DELETE (DELETE USER)
    // curl.exe -X DELETE -d "0" http://localhost:8080/users

}
