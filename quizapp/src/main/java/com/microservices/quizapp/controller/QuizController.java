package com.microservices.quizapp.controller;

import com.microservices.quizapp.pojo.Answer;
import com.microservices.quizapp.pojo.Response;
import com.microservices.quizapp.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@Slf4j
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Response> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        log.info("QuizController :: createQuiz()");
        return this.quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<Response> getQuizById(@PathVariable int id) {
        log.info("QuizController :: getQuizById()");
        return this.quizService.getQuizById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllQuiz() {
        log.info("QuizController :: getAllQuiz()");
        return this.quizService.getAllQuiz();
    }

    @PostMapping("/submit/quiz")
    public ResponseEntity<Response> submitQuiz(@RequestBody List<Answer> answerList) {
        log.info("QuizController :: submitQuiz()");
        return this.quizService.getScore(answerList);
    }

}
