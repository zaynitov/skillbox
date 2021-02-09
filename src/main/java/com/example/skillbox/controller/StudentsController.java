package com.example.skillbox.controller;

import com.example.skillbox.entity.StudentDto;
import com.example.skillbox.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class StudentsController {
    @Autowired
    StudentsService studentsService;

    @RequestMapping(value = "/savestudent",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto) {
        studentsService.save(studentDto);
        return ResponseEntity.ok().build();
    }
    //аналогично можно дописать другие
}
