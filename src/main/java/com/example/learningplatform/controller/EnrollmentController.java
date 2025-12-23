package com.example.learningplatform.controller;

import com.example.learningplatform.dto.EnrollmentRequestDto;
import com.example.learningplatform.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public String enroll(@RequestBody EnrollmentRequestDto dto) {
        enrollmentService.enrollStudent(dto.getStudentId(), dto.getCourseId());
        return "Студент успешно записан на курс";
    }
}