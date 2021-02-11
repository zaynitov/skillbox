package com.example.skillbox.service;


import com.example.skillbox.entity.StudentDto;
import com.example.skillbox.model.Student;
import com.example.skillbox.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
        log.debug("deleted student with id: {}", id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getById(Long studentId) {
        return studentRepository.findById(studentId);
    }
}
