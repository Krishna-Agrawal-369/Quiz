package com.microservices.quizservice.feign;

import com.microservices.quizservice.pojo.Answer;
import com.microservices.quizservice.pojo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/question/generate")
    ResponseEntity<Response> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);
    @PostMapping("/question/getQuestions")
    ResponseEntity<Response> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    @PostMapping("/question/getScore")
    ResponseEntity<Response> getScore(@RequestBody List<Answer> answerList);
}
