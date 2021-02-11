package com.example.skillbox.service;


import com.example.skillbox.entity.StudentDto;
import com.example.skillbox.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentsService {
    void save(StudentDto student);

    void deleteById(Long id);

    List<Student> getAll();

    Optional<Student> getById(Long studentId);
}
