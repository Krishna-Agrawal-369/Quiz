package com.microservices.quizservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
    private String categoryName;
    private Integer numQuestions;
    private String title;
}
