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

    // Create student
    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Get all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // Get a student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getStudentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ErrorResponse(e.getMessage())
            );
        }
    }

    // Update a student by id
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student ) {

        try {
            return ResponseEntity.ok(studentService.updateStudent(id, student));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    // Delete a student by id
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }
}
