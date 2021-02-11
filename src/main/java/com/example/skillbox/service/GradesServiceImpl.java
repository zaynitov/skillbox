package com.example.skillbox.service;

import com.example.skillbox.exceptions.NoGradeException;
import com.example.skillbox.exceptions.NoStudentException;
import com.example.skillbox.model.Grades;
import com.example.skillbox.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class GradesServiceImpl implements GradesService {
    @Autowired
    StudentsService studentsService;
    @Autowired
    SubjectService subjectService;

    @Override
    public int calculateGradeBySubjectForStudent(Long studentId, String subject) {
        Optional<Student> studentOpt = studentsService.getById(studentId);
        if (studentOpt.isEmpty()) {
            throw new NoStudentException("Student was not founded");
        }
        Student student = studentOpt.get();
        Set<Grades> grades = student.getGrades();
        int sumGrade = 0;
        int count = 0;
        for (Grades grade : grades) {
            if (Objects.equals(grade.getSubject().getName(), subject)) {
                sumGrade += grade.getGrade();
                count++;
            }
        }
        if (count == 0) {
            throw new NoGradeException("No grades");
        }
        return sumGrade / count;
    }

    @Override
    public int calculateAvgGradeForStudent(Long studentId) {
        Optional<Student> studentOpt = studentsService.getById(studentId);
        if (studentOpt.isEmpty()) {
            throw new NoStudentException("Student was not found");
        }
        Student student = studentOpt.get();
        Set<Grades> grades = student.getGrades();
        if (grades.size() == 0) {
            throw new NoGradeException("No grades");
        }
        int sumGrade = 0;
        for (Grades grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade / grades.size();
    }
}
