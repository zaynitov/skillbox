package com.example.skillbox.entity;

import com.example.skillbox.model.Subject;
import lombok.Data;

@Data
public class SubjectDto {
    private String name;

    public static Subject transformToModel(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        return subject;
    }
}
