package com.example.learningplatform.controller;

import com.example.learningplatform.dto.EnrollmentRequestDto;
import com.example.learningplatform.service.EnrollmentService;
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

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService; // Здесь мокаем сервис, а не репозиторий

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void enrollStudent_ShouldReturnOk() throws Exception {
        EnrollmentRequestDto dto = new EnrollmentRequestDto();
        dto.setStudentId(1L);
        dto.setCourseId(2L);

        // Сервис ничего не возвращает (void), поэтому просто проверяем статус ответа
        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Студент успешно записан на курс"));
    }
}