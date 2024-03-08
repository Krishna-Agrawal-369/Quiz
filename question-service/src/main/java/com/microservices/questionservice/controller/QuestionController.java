package com.microservices.questionservice.controller;


import com.microservices.questionservice.pojo.Answer;
import com.microservices.questionservice.pojo.Question;
import com.microservices.questionservice.pojo.Response;
import com.microservices.questionservice.service.QuestionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<Response> getAllQuestions(@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                    @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "sortBy", defaultValue = "questionId", required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "AESC", required = false) String sortDir) {
        log.info("QuestionController :: getAllQuestions()");
        return new ResponseEntity<>(new Response(true, 0, "Questions list fetched successfully", this.questionService.getAllQuestions(pageSize, pageNumber, sortBy, sortDir)), HttpStatus.OK);
    }

    @GetMapping("/questionById/{id}")
    public ResponseEntity<Response> getQuestionById(@PathVariable("id") Integer questionId) {
        log.info("QuestionController :: getQuestionById()");
        return this.questionService.getQuestionById(questionId);
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<Response> searchQuestionByTitle(@RequestParam("title") String questionTitle) {
        log.info("QuestionController :: searchQuestionByTitle()");
        return this.questionService.searchQuestionByTitle(questionTitle);
    }

    @GetMapping("/questionByCategory")
    public ResponseEntity<Response> getQuestionByCategory(@RequestParam String category) {
        log.info("QuestionController :: getQuestionByCategory()");
        return this.questionService.getQuestionByCategory(category);
    }

    @GetMapping("/questionByCategoryAndDifficulty")
    public ResponseEntity<Response> getQuestionByCategoryAndDifficulty(@RequestParam String category, @RequestParam String difficultyLevel) {
        log.info("QuestionController :: getQuestionByCategoryAndDifficulty()");
        return this.questionService.getQuestionByCategoryAndDifficulty(category, difficultyLevel);
    }

    @PostMapping("/add/question")
    public ResponseEntity<Response> addQuestion(@RequestBody @Valid Question question) {
        log.info("QuestionController :: addQuestion()");
        this.questionService.addQuestion(question);
        return new ResponseEntity<>(new Response(true, 0, "Question added successfully"), HttpStatus.OK);
    }

    @PostMapping("/add/questions")
    public ResponseEntity<Response> addQuestions(@RequestBody List<@Valid Question> questionList) {
        log.info("QuestionController :: addQuestions()");
        this.questionService.addQuestions(questionList);
        return new ResponseEntity<>(new Response(true, 0, "Questions list added successfully"), HttpStatus.OK);
    }

    @PutMapping("/update/question/{id}")
    public ResponseEntity<Response> updateQuestion(@PathVariable("id") Integer questionId, @RequestBody @Valid Question question) {
        log.info("QuestionController :: updateQuestion()");
        return this.questionService.updateQuestion(questionId, question);
    }

    @DeleteMapping("/delete/question")
    public ResponseEntity<Response> deleteQuestionById(@RequestParam Integer questionId) {
        log.info("QuestionController :: deleteQuestionById()");
        return this.questionService.deleteQuestionById(questionId);
    }

    @GetMapping("/generate")
    public ResponseEntity<Response> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ) {
        log.info("QuestionController :: getQuestionsForQuiz()");
        return this.questionService.getQuestionsForQuiz(category,numQ);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<Response> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        log.info("QuestionController :: getQuestionsFromId()");
        return this.questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Response> getScore(@RequestBody List<Answer> answerList) {
        log.info("QuestionController :: getScore()");
        return this.questionService.getScore(answerList);
    }

}
