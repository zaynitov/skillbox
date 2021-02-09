package com.example.skillbox.service;

public interface GradesService {
    int calculateGradeBySubjectForStudent(Long studentId, Long subjectId);

    int calculateAvgGradeForStudent(Long studentId);
}
