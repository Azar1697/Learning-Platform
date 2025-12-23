package com.example.learningplatform.dto;

import lombok.Data;

@Data
public class SubmissionRequestDto {
    private Long studentId;
    private Long assignmentId;
    private String content;
}