package com.microservices.quizapp.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer implements Serializable {
    @Serial
    private static final long serialVersionUID = 8016642192477950738L;
    private Integer questionId;
    private String response;
}
