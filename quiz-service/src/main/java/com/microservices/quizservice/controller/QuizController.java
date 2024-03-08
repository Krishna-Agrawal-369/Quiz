package com.microservices.quizservice.controller;

import com.microservices.quizservice.pojo.Answer;
import com.microservices.quizservice.pojo.QuizDto;
import com.microservices.quizservice.pojo.Response;
import com.microservices.quizservice.service.QuizService;
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
    public ResponseEntity<Response> createQuiz(@RequestBody QuizDto quizDto) {
        log.info("QuizController :: createQuiz()");
        return this.quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
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

    @GetMapping("/getQuestions")
    public ResponseEntity<Response> getQuizQuestions(@RequestParam Integer id) {
        log.info("QuizController :: getQuizQuestions()");
        return this.quizService.getQuizQuestions(id);
    }

}
