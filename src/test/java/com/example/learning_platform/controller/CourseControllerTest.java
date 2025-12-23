package com.example.learningplatform.controller;

import com.example.learningplatform.dto.CourseDto;
import com.example.learningplatform.entity.Course;
import com.example.learningplatform.entity.User;
import com.example.learningplatform.repository.CourseRepository;
import com.example.learningplatform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class) 
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private CourseRepository courseRepository; 

    @MockBean
    private UserRepository userRepository; 

    // ТЕСТ 1: Получение всех курсов
    @Test
    public void getAllCourses_ShouldReturnList() throws Exception {

        User teacher = new User();
        teacher.setName("Test Teacher");

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Java Basics");
        course.setDescription("Intro to Java");
        course.setTeacher(teacher);

        
        Mockito.when(courseRepository.findAll()).thenReturn(List.of(course));

        // 2. Выполнение запроса и проверка
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$[0].title").value("Java Basics")) 
                .andExpect(jsonPath("$[0].teacherName").value("Test Teacher"));
    }

    // ТЕСТ 2: Получение курса по ID
    @Test
    public void getCourseById_ShouldReturnCourse() throws Exception {
        User teacher = new User();
        teacher.setName("Test Teacher");
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Java Basics");
        course.setTeacher(teacher);

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Basics"));
    }
}