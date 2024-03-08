package com.microservices.quizservice.serviceImpl;

import com.microservices.quizservice.feign.QuizInterface;
import com.microservices.quizservice.pojo.Answer;
import com.microservices.quizservice.pojo.Quiz;
import com.microservices.quizservice.pojo.Response;
import com.microservices.quizservice.repository.QuizRepository;
import com.microservices.quizservice.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizInterface quizInterface;

    @Override
    public ResponseEntity<Response> createQuiz(String category, int numQ, String title) {
        log.info("QuizService :: createQuiz()");
        List<Integer> questions = (List<Integer>) this.quizInterface.getQuestionsForQuiz(category, numQ).getBody().getData();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>(new Response(true, 0, "Quiz created successfully"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> getQuizById(int id) {
        log.info("QuizService :: getQuizById()");
        Optional<Quiz> quiz = this.quizRepository.findById(id);
        if (quiz.isPresent())
            return new ResponseEntity<>(new Response(true, 0, "Quiz with id : " + id + " fetched successfully", quiz), HttpStatus.OK);
        return new ResponseEntity<>(new Response(false, -1, "No quiz found with provided id : " + id), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Response> getAllQuiz() {
        log.info("QuizService :: getAllQuiz()");
        return new ResponseEntity<>(new Response(true, 0, "Quiz fetched successfully", this.quizRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getQuizQuestions(int id) {
        log.info("QuizService :: getQuizQuestions()");
        Optional<Quiz> quiz = this.quizRepository.findById(id);
        List<Integer> questionIds = quiz.get().getQuestions();
        return this.quizInterface.getQuestionsFromId(questionIds);
    }

    @Override
    public ResponseEntity<Response> getScore(List<Answer> answerList) {
        log.info("QuizService :: getScore()");
        return this.quizInterface.getScore(answerList);
    }
}
