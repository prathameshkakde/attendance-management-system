package com.example.attendance.controller;

import com.example.attendance.model.Student;
import com.example.attendance.model.ErrorResponse;
import com.example.attendance.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class HomeController {

    private final StudentService studentService;

    // Constructor injection
    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Show students in UI
    @GetMapping("/students-view")
    public String showStudents(Model model) {

        model.addAttribute("students", studentService.getStudents());

        return "students";
    }

    // Create student api
    @PostMapping("/students")
    @ResponseBody
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Create student UI
    @PostMapping("/students/add")
    public String addStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/students-view";
    }


    // Get all students
    @GetMapping("/students")
    @ResponseBody
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // Get a student by id
    @GetMapping("/students/{id}")
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<?> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student ) {

        try {
            return ResponseEntity.ok(studentService.updateStudent(id, student));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    // Delete a student by id ui
    @DeleteMapping("/students/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    // Delete a student by id api
    @PostMapping("/students/delete/{id}")
    public String deleteStudentFromUI(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students-view";
    }
}
