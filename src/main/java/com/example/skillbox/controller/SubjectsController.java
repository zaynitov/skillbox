package com.example.skillbox.controller;

import com.example.skillbox.entity.SubjectDto;
import com.example.skillbox.service.SubjectService;
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
public class SubjectsController {
    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/savesubject",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> saveSubject(@RequestBody SubjectDto subjectDto) {
        subjectService.save(subjectDto);
        return ResponseEntity.ok().build();
    }
    //аналогично можно дописать другие
}
