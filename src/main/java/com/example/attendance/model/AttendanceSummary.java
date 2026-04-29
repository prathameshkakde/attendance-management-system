package com.example.attendance.model;

// This class represents summary response
public class AttendanceSummary {

    private long totalPresent;
    private long totalAbsent;
    private double percentage;

    public AttendanceSummary(long totalPresent, long totalAbsent, double percentage) {
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.percentage = percentage;
    }

    public long getTotalPresent() {
        return totalPresent;
    }

    public long getTotalAbsent() {
        return totalAbsent;
    }

    public double getPercentage() {
        return percentage;
    }
}
