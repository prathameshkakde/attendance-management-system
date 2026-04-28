package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.Student;
import com.example.attendance.repository.StudentRepository;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentRepository studentRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    // Mark attendance
    public Attendance markAttendance(Long studentId, String status) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        Attendance attendance = new Attendance( student, LocalDate.now(), status);

        return attendanceRepository.save(attendance);
    }

    // Get all attendance records
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Get attendance by student
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    // Get attendance by date
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    // Get attendance by student and date
    public List<Attendance> getAttendanceByStudentAndDate(Long studentId, LocalDate date) {
        return attendanceRepository.findByStudentIdAndDate(studentId, date);
    }
}
