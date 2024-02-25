package com.example.employeemanagementsystem.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object errors, String message, Object responseObj) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", status.value());
        response.put("message", message);
        response.put("data", responseObj);
        response.put("errors", errors);

        return new ResponseEntity<>(response, status);
    }
}
