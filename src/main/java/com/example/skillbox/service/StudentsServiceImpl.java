package com.example.skillbox.service;


import com.example.skillbox.entity.StudentDto;
import com.example.skillbox.model.Student;
import com.example.skillbox.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.skillbox.entity.StudentDto.transformToModel;

@Service
@Slf4j
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void save(StudentDto student) {
        studentRepository.save(transformToModel(student));
        log.debug("saved student: {}", student);
    }

    @Override
    public Optional<Student> getById(Long studentId) {
       return studentRepository.findById(studentId);
    }
}
