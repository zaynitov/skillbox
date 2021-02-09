package com.example.skillbox.repository;

import com.example.skillbox.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    void delete(Student deleted);

    List<Student> findAll();

    Student save(Student persisted);
}
