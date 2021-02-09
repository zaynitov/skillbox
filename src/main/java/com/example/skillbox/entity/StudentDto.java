package com.example.skillbox.entity;

import com.example.skillbox.model.Student;
import lombok.Data;

@Data
public class StudentDto {
    private String name;
    private String surname;
    private String patronymic;

    public static Student transformToModel(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setPatronymic(studentDto.getPatronymic());
        return student;
    }
}
