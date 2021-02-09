package com.example.skillbox.repository;

import com.example.skillbox.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    void delete(Subject deleted);

    List<Subject> findAll();

    Subject save(Subject persisted);
}
