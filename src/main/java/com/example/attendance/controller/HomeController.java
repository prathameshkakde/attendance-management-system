package com.example.attendance.controller;

import com.example.attendance.model.Student;
import com.example.attendance.service.StudentService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/student")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
