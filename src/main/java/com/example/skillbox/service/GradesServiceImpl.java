package com.example.skillbox.service;

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
    public int calculateGradeBySubjectForStudent(Long studentId, Long subjectId) {
        Optional<Student> studentOpt = studentsService.getById(studentId);
        Student student = studentOpt.get();
        Set<Grades> grades = student.getGrades();
        int sumGrade = 0;
        int count = 0;
        for (Grades grade : grades) {
            if (Objects.equals(grade.getSubject().getId(), subjectId)) {
                sumGrade += grade.getGrade();
                count++;
            }
        }
        //fix situation when count=0
        return sumGrade / count;
    }

    @Override
    public int calculateAvgGradeForStudent(Long studentId) {
        Optional<Student> studentOpt = studentsService.getById(studentId);
        //fix if null
        Student student = studentOpt.get();
        Set<Grades> grades = student.getGrades();
        int sumGrade = 0;
        for (Grades grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade / grades.size();
    }
}
