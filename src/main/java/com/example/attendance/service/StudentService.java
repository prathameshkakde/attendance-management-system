package com.example.attendance.service;

import com.example.attendance.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    public Student getStudent() {
        return new Student(1L, "John Doe", "john@example.com");
    }

    public List<Student> getStudents() {
        return Arrays.asList(
                new Student(1L, "John Doe", "john@example.com"),
                new Student(2L, "Jane Smith", "jane@example.com")
        );
    }
}
