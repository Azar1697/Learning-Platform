package com.example.learningplatform.controller;

import com.example.learningplatform.dto.SubmissionRequestDto;
import com.example.learningplatform.service.HomeworkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeworkController.class)
public class HomeworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeworkService homeworkService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void submitHomework_ShouldReturnOk() throws Exception {
        SubmissionRequestDto dto = new SubmissionRequestDto();
        dto.setStudentId(1L);
        dto.setAssignmentId(5L);
        dto.setContent("My Homework Solution");

        mockMvc.perform(post("/api/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Задание успешно отправлено на проверку"));
    }
}