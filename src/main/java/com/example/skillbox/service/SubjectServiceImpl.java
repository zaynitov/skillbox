package com.example.skillbox.service;

import com.example.skillbox.entity.SubjectDto;
import com.example.skillbox.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.skillbox.entity.SubjectDto.*;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public void save(SubjectDto subjectDto) {
        subjectRepository.save(transformToModel(subjectDto));
    }
}
