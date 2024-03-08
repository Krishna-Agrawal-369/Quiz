package com.microservices.questionservice.service;


import com.microservices.questionservice.pojo.Answer;
import com.microservices.questionservice.pojo.Question;
import com.microservices.questionservice.pojo.Response;
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

    ResponseEntity<Response> getQuestionsForQuiz(String category, Integer numQ);

    ResponseEntity<Response> getQuestionsFromId(List<Integer> questionIds);

    ResponseEntity<Response> getScore(List<Answer> answerList);
}
