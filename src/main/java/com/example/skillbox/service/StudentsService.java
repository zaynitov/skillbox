package com.example.skillbox.service;


import com.example.skillbox.entity.StudentDto;
import com.example.skillbox.model.Student;

import java.util.Optional;

public interface StudentsService {
    void save(StudentDto student);
    Optional<Student> getById(Long studentId);
}
