package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.AttendanceSummary;
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

    // Get attendance summary by student
    public AttendanceSummary getAttendanceSummary(Long studentId) {

        List<Attendance> records = attendanceRepository.findByStudentId(studentId);

        long totalPresent = records.stream().filter(a -> "Present".equalsIgnoreCase(a.getStatus())).count();

        long totalAbsent = records.stream().filter(a -> "Absent".equalsIgnoreCase(a.getStatus())).count();

        long total = totalPresent + totalAbsent;

        double percentage = total == 0 ? 0 : (totalPresent * 100.0) / total;

        return new AttendanceSummary(totalPresent, totalAbsent, percentage);
    }
}
