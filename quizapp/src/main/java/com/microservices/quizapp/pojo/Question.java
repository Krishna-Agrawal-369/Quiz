package com.microservices.quizapp.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = 3976888596671256921L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer questionId;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{3,255}$", message = "Input string length must be greater than 3 and string must be alphabetic only")
    private String questionTitle;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z\s]{3,100}$", message = "Input string length must be greater than 3 and string must be alphabetic only")
    private String category;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z\s]{3,100}$", message = "Input string length must be greater than 3 and string must be alphabetic only")
    private String difficultyLevel;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{1,255}$", message = "Input string length must be greater than 1 and string must be alphabetic only")
    private String option1;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{1,255}$", message = "Input string length must be greater than 1 and string must be alphabetic only")
    private String option2;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{1,255}$", message = "Input string length must be greater than 1 and string must be alphabetic only")
    private String option3;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{1,255}$", message = "Input string length must be greater than 1 and string must be alphabetic only")
    private String option4;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{1,255}$", message = "Input string length must be greater than 1 and string must be alphabetic only")
    private String rightAnswer;
}
