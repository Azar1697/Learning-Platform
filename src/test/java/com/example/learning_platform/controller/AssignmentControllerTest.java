package com.example.learningplatform.controller;

import com.example.learningplatform.entity.Assignment;
import com.example.learningplatform.entity.Course;
import com.example.learningplatform.entity.Lesson;
import com.example.learningplatform.entity.Module;
import com.example.learningplatform.repository.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssignmentController.class)
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentRepository assignmentRepository;

    @Test
    public void getAssignmentsByCourse_ShouldReturnFilteredList() throws Exception {
        // Создаем цепочку зависимостей: Assignment -> Lesson -> Module -> Course
        Course course = new Course();
        course.setId(10L); // Ищем курс с ID 10

        Module module = new Module();
        module.setCourse(course);

        Lesson lesson = new Lesson();
        lesson.setModule(module);

        Assignment assignment = new Assignment();
        assignment.setId(100L);
        assignment.setTitle("Test Task");
        assignment.setLesson(lesson);

        // Мокаем репозиторий
        Mockito.when(assignmentRepository.findAll()).thenReturn(List.of(assignment));

        // Выполняем запрос
        mockMvc.perform(get("/api/assignments/course/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"));
    }
}