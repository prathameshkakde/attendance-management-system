package com.example.attendance.service;

import com.example.attendance.model.Student;
import com.example.attendance.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get a Student
    public Student getStudent() {
        return new Student(1L, "John Doe", "john@example.com");
    }

    // Get Student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // Get all Student
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // Update Student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());

        return studentRepository.save(existingStudent);
    }

    // Delete Student
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }
}
