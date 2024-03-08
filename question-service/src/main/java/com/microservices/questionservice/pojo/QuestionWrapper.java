package com.microservices.questionservice.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 5489981219874676579L;

    private @NonNull Integer questionId;
    @NotEmpty(message = "Input string cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{3,255}$", message = "Input string length must be greater than 3 and string must be alphabetic only")
    private String questionTitle;
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
}
