package com.example.learningplatform.controller;

import com.example.learningplatform.dto.AssignmentDto;
import com.example.learningplatform.repository.AssignmentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentRepository assignmentRepository;

    public AssignmentController(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping("/course/{courseId}")
    public List<AssignmentDto> getAssignmentsByCourse(@PathVariable Long courseId) {
        return assignmentRepository.findAll().stream()
                .filter(a -> a.getLesson().getModule().getCourse().getId().equals(courseId))
                .map(a -> {
                    AssignmentDto dto = new AssignmentDto();
                    dto.setId(a.getId());
                    dto.setTitle(a.getTitle());
                    dto.setDescription(a.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}