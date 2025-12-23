package com.example.learningplatform.controller;

import com.example.learningplatform.dto.SubmissionRequestDto;
import com.example.learningplatform.entity.Submission;
import com.example.learningplatform.service.HomeworkService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @PostMapping
    public String submit(@RequestBody SubmissionRequestDto dto) {
        homeworkService.submitWork(dto.getStudentId(), dto.getAssignmentId(), dto.getContent());
        return "Задание успешно отправлено на проверку";
    }
}