package com.example.skillbox.controller;

import com.example.skillbox.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class GradesController {
    @Autowired
    GradesService gradesService;

    @RequestMapping(value = "/totalavggrade",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> calculateTotalAvgGrade(@RequestParam Long studentId) {
        int avgGrade = gradesService.calculateAvgGradeForStudent(studentId);
        return ResponseEntity.ok().body(avgGrade);
    }

    @RequestMapping(value = "/avggrade",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> calculateAvgGrade(@RequestParam Long studentId, @RequestParam Long subjectId) {
        int avgGrade = gradesService.calculateGradeBySubjectForStudent(studentId, subjectId);
        return ResponseEntity.ok().body(avgGrade);
    }
}
