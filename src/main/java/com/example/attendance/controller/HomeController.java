package com.example.attendance.controller;

import com.example.attendance.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Attendance System is Running!";
    }

    @GetMapping("/student")
    public Student getStudent() {
        return new Student(1L, "John Doe", "john@example.com");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return Arrays.asList(
                new Student(1L, "John Doe", "john@example.com"),
                new Student(2L, "Jane Smith", "jane@example.com")
                );
    }
}
