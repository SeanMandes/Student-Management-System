package backend.controller;

import backend.dao.InstructorDAO;
import backend.model.Instructor;
import backend.model.Student;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InstructorApiController implements HttpHandler {
    public static final InstructorDAO instructorDAO = new InstructorDAO();
    public static List<Instructor> instructors = new ArrayList<>();
    public static List<List<String>> instructors_info = new ArrayList<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        System.out.println("THIS IS THE METHOD: " + method);
        switch (method){
            case "GET" -> handleGetInstructor(exchange);
//            case "POST" -> handlePostInstructor(exchange);
//            case "PUT" -> handlePutInstructor(exchange);
//            case "DELETE" -> handleDeleteInstructor(exchange);
            default -> sendResponse(exchange, 405, "Method Not Allowed");
        }
    }

    private void handleGetInstructor(HttpExchange exchange) throws IOException {
        instructors = instructorDAO.viewInstructor();
        for (Instructor instructor : instructors){
            List<String> info = new ArrayList<>();
            info.add(String.valueOf(instructor.getId()));
            info.add(instructor.getInstructorName());
            info.add(instructor.getInstructorEmail());
            instructors_info.add(info);
        }
        sendResponse(exchange, 200, String.valueOf(instructors_info));
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

}
