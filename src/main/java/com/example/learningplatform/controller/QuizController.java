package com.example.learningplatform.controller;

import com.example.learningplatform.dto.QuizDto;
import com.example.learningplatform.entity.Question;
import com.example.learningplatform.repository.QuizRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizRepository quizRepository;

    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/course/{courseId}")
    public List<QuizDto> getQuizzesByCourse(@PathVariable Long courseId) {
        return quizRepository.findAll().stream()
                .filter(q -> q.getModule().getCourse().getId().equals(courseId))
                .map(q -> {
                    QuizDto dto = new QuizDto();
                    dto.setId(q.getId());
                    dto.setTitle(q.getTitle());
                    dto.setQuestions(q.getQuestions().stream()
                            .map(Question::getText)
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}