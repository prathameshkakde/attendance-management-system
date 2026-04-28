package com.example.attendance.controller;

import com.example.attendance.model.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import com.example.attendance.service.AttendanceService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    // Mark attendance api
    @PostMapping("/mark")
    public Attendance markAttendance(@RequestParam Long studentId, @RequestParam String status) {
        return attendanceService.markAttendance(studentId, status);
    }

    // View attendance api
    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // View attendance for a student by id api
    @GetMapping("/student/{id}")
    public List<Attendance> getAttendanceByStudent(@PathVariable Long id) {
        return attendanceService.getAttendanceByStudent(id);
    }
}
