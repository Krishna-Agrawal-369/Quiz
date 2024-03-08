package com.microservices.quizapp.service;

import com.microservices.quizapp.pojo.Question;
import com.microservices.quizapp.pojo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<Question> getAllQuestions(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);
    ResponseEntity<Response> getQuestionById(Integer questionId);
    ResponseEntity<Response> getQuestionByCategory(String category);
    ResponseEntity<Response> getQuestionByCategoryAndDifficulty(String category, String difficultyLevel);
    void addQuestion(Question question);
    void addQuestions(List<Question> questionList);
    ResponseEntity<Response> updateQuestion(Integer questionId, Question question);
    ResponseEntity<Response> deleteQuestionById(Integer questionId);
    ResponseEntity<Response> searchQuestionByTitle(String questionTitle);
}
