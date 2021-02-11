package com.example.skillbox;

import com.example.skillbox.model.Grades;
import com.example.skillbox.model.Student;
import com.example.skillbox.model.Subject;
import com.example.skillbox.service.StudentsService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AvgGradeBySubjectApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentsService studentsService;

    private final Long STUDENT_ID = 13L;
    private final String SUBJECT = "Math";

    @Test
    public void getAvgGrade() throws Exception {
        Subject subject = new Subject();
        Subject subjectWrong = new Subject();
        subject.setName(SUBJECT);
        subjectWrong.setName("Phys");

        Grades grade1 = new Grades();
        grade1.setSubject(subject);
        grade1.setGrade(3);

        Grades grade2 = new Grades();
        grade2.setSubject(subject);
        grade2.setGrade(5);

        Grades grade3 = new Grades();
        grade3.setSubject(subjectWrong);
        grade3.setGrade(9);

        Student stubbedStudent = new Student();
        stubbedStudent.setId(STUDENT_ID);
        stubbedStudent.setGrades(Sets.newSet(grade1, grade2, grade3));
        when(studentsService.getById(STUDENT_ID)).thenReturn(Optional.of(stubbedStudent));

        mockMvc.perform(get("/avggrade?studentId=" + STUDENT_ID + "&subject=" + SUBJECT))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("4")));
    }

    @Test
    public void getAvgGradeIfNoGrades() throws Exception {
        Student stubbedStudent = new Student();
        stubbedStudent.setId(STUDENT_ID);
        stubbedStudent.setGrades(Collections.emptySet());
        when(studentsService.getById(STUDENT_ID)).thenReturn(Optional.of(stubbedStudent));

        mockMvc.perform(get("/avggrade?studentId=" + STUDENT_ID + "&subject=" + SUBJECT))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("No grades")));
    }


    @Test
    public void getAvgGradeIfNoStudent() throws Exception {
        when(studentsService.getById(STUDENT_ID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/avggrade?studentId=" + STUDENT_ID + "&subject=" + SUBJECT))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Student was not found")));
    }

    @Test
    public void getAvgGradeIfRuntime() throws Exception {
        when(studentsService.getById(STUDENT_ID)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/avggrade?studentId=" + STUDENT_ID + "&subject=" + SUBJECT))
                .andExpect(status().is5xxServerError());
    }
}
