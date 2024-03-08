package com.microservices.quizservice.service;

import com.microservices.quizservice.pojo.Answer;
import com.microservices.quizservice.pojo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    ResponseEntity<Response> createQuiz(String category, int numQ, String title);

    ResponseEntity<Response> getQuizById(int id);

    ResponseEntity<Response> getAllQuiz();

    ResponseEntity<Response> getScore(List<Answer> answerList);
    ResponseEntity<Response> getQuizQuestions(int id);
}
