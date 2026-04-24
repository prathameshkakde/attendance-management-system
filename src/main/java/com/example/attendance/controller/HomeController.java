package com.example.attendance.controller;

import com.example.attendance.model.Student;
import com.example.attendance.model.ErrorResponse;
import com.example.attendance.service.StudentService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class HomeController {

    private final StudentService studentService;

    // Constructor injection
    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello, Attendance System is Running!";
    }

    @GetMapping("/student")
    public Student getStudent() {
        return studentService.getStudent();
    }

    // API to get Student by id
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getStudentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ErrorResponse(e.getMessage())
            );
        }
    }

    // API to update student
    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student ) {

        try {
            return ResponseEntity.ok(studentService.updateStudent(id, student));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    // API to get all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/student")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
