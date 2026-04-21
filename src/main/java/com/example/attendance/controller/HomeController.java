package com.example.attendance.controller;

import com.example.attendance.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Attendance System is Running!";
    }

    @GetMapping("/student")
    public Student getStudent() {
        Student student = new Student(1L, "John Doe", "john@example.com");

        return student;
    }
}
