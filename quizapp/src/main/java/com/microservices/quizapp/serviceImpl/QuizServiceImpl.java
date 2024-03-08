package com.microservices.quizapp.serviceImpl;

import com.microservices.quizapp.pojo.Answer;
import com.microservices.quizapp.pojo.Question;
import com.microservices.quizapp.pojo.Quiz;
import com.microservices.quizapp.pojo.Response;
import com.microservices.quizapp.repository.QuestionRepository;
import com.microservices.quizapp.repository.QuizRepository;
import com.microservices.quizapp.service.QuizService;
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
    private QuestionRepository questionRepository;
    @Override
    public ResponseEntity<Response> createQuiz(String category, int numQ, String title) {
        log.info("QuizService :: createQuiz()");
        List<Question> questionList = this.questionRepository.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
        return new ResponseEntity<>(new Response(true,0,"Quiz created successfully"), HttpStatus.CREATED);
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
        return new ResponseEntity<>(new Response(true,0,"Quiz fetched successfully", this.quizRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getScore(List<Answer> answerList) {
        log.info("QuizService :: getScore()");
        long score = answerList.stream().filter(a->this.questionRepository.findById(a.getQuestionId()).get().getRightAnswer().equalsIgnoreCase(a.getResponse())).count();
        return new ResponseEntity<>(new Response(true,0,"Score : " + score),HttpStatus.OK);
    }
}
