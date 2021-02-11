package com.example.skillbox.service;

public interface GradesService {
    int calculateGradeBySubjectForStudent(Long studentId, String subject);

    int calculateAvgGradeForStudent(Long studentId);
}
