package com.example.attendance.repository;

import com.example.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByDate(LocalDate date);

    List<Attendance> findByStudentIdAndDate(Long studentId, LocalDate date);

    @Transactional
    @Modifying
    void deleteByStudentId(Long studentId);
}
