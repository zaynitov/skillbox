package com.example.skillbox;

import com.example.skillbox.model.Grades;
import com.example.skillbox.model.Student;
import com.example.skillbox.service.StudentsService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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
class TotalAvgGradeApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentsService studentsService;

    private final Long STUDENT_ID = 13L;

    @Test
    public void getTotalAvgGrade() throws Exception {
        Grades grade1 = new Grades();
        grade1.setGrade(3);
        Grades grade2 = new Grades();
        grade2.setGrade(5);
        Student stubbedStudent = new Student();
        stubbedStudent.setId(STUDENT_ID);
        stubbedStudent.setGrades(Sets.newSet(grade1, grade2));
        when(studentsService.getById(STUDENT_ID)).thenReturn(Optional.of(stubbedStudent));

        mockMvc.perform(get("/totalavggrade?studentId=" + STUDENT_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("4")));
    }

    @Test
    public void getTotalAvgGradeIfNoGrades() throws Exception {
        Student stubbedStudent = new Student();
        stubbedStudent.setId(STUDENT_ID);
        stubbedStudent.setGrades(Collections.emptySet());
        when(studentsService.getById(STUDENT_ID)).thenReturn(Optional.of(stubbedStudent));

        mockMvc.perform(get("/totalavggrade?studentId=" + STUDENT_ID))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("No grades")));
    }


    @Test
    public void getTotalAvgGradeIfNoStudent() throws Exception {
        when(studentsService.getById(STUDENT_ID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/totalavggrade?studentId=" + STUDENT_ID))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Student was not found")));
    }

    @Test
    public void getTotalAvgGradeIfRuntime() throws Exception {
        when(studentsService.getById(STUDENT_ID)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/totalavggrade?studentId=" + STUDENT_ID))
                .andExpect(status().is5xxServerError());
    }
}
