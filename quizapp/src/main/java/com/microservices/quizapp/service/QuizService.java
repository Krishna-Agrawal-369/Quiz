package com.microservices.quizapp.service;

import com.microservices.quizapp.pojo.Answer;
import com.microservices.quizapp.pojo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    ResponseEntity<Response> createQuiz(String category, int numQ, String title);

    ResponseEntity<Response> getQuizById(int id);

    ResponseEntity<Response> getAllQuiz();

    ResponseEntity<Response> getScore(List<Answer> answerList);
}
