package com.microservices.quizservice.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quiz implements Serializable {
    @Serial
    private static final long serialVersionUID = 7057995512326095036L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer quizId;
    private String title;
    @ElementCollection
    private List<Integer> questions;
}
