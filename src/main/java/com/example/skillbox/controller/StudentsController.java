package com.example.skillbox.controller;

import com.example.skillbox.entity.StudentDto;
import com.example.skillbox.model.Student;
import com.example.skillbox.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentsController {
    @Autowired
    StudentsService studentsService;

    @RequestMapping(value = "/savestudent",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto) {
        studentsService.save(studentDto);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(value = "/deletestudent",
            method = RequestMethod.POST)
    public ResponseEntity<?> deleteStudent(@RequestParam Long id) {
        studentsService.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(value = "/getallstudents",
            method = RequestMethod.POST)
    public ResponseEntity<?> getAllStudents() {
        List<Student> allStudents = studentsService.getAll();
        return ResponseEntity
                .ok()
                .body(allStudents);
    }
}
