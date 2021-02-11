package com.example.skillbox.controller;

import com.example.skillbox.exceptions.NoGradeException;
import com.example.skillbox.exceptions.NoStudentException;
import com.example.skillbox.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradesController {
    @Autowired
    GradesService gradesService;

    @RequestMapping(value = "/totalavggrade",
            method = RequestMethod.GET)
    public ResponseEntity<?> calculateTotalAvgGrade(@RequestParam Long studentId) {
        int avgGrade = gradesService.calculateAvgGradeForStudent(studentId);
        return ResponseEntity
                .ok()
                .body(avgGrade);
    }

    @RequestMapping(value = "/avggrade",
            method = RequestMethod.GET)
    public ResponseEntity<?> calculateAvgGrade(@RequestParam Long studentId, @RequestParam String subject) {
        int avgGrade = gradesService.calculateGradeBySubjectForStudent(studentId, subject);
        return ResponseEntity
                .ok()
                .body(avgGrade);
    }

    @ExceptionHandler({NoGradeException.class, NoStudentException.class})
    public ResponseEntity<?> exception(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<?> allException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
