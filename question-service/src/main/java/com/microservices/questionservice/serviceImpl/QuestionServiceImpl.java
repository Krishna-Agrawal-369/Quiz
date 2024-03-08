package com.microservices.questionservice.serviceImpl;

import com.microservices.questionservice.pojo.Answer;
import com.microservices.questionservice.pojo.Question;
import com.microservices.questionservice.pojo.QuestionWrapper;
import com.microservices.questionservice.pojo.Response;
import com.microservices.questionservice.repository.QuestionRepository;
import com.microservices.questionservice.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
        log.info("QuestionService :: getAllQuestions()");
        Sort sort = sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return this.questionRepository.findAll(pageable).getContent();
    }

    @Override
    public ResponseEntity<Response> getQuestionById(Integer questionId) {
        log.info("QuestionService :: getEmployeeById()");
        Optional<Question> question = this.questionRepository.findById(questionId);
        if (question.isPresent())
            return new ResponseEntity<>(new Response(true, 0, "Question with id : " + questionId + " fetched successfully", question), HttpStatus.OK);
        return new ResponseEntity<>(new Response(false, -1, "No question found with provided id : " + questionId), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Response> getQuestionByCategory(String category) {
        log.info("QuestionService :: getQuestionByCategory()");
        List<Question> questionList = this.questionRepository.findByCategory(category);
        if (questionList.isEmpty())
            return new ResponseEntity<>(new Response(false, -1, "No question found with provided category : " + category), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Response(true, 0, "Questions with category : " + category + " fetched successfully", questionList), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Response> getQuestionByCategoryAndDifficulty(String category, String difficultyLevel) {
        log.info("QuestionService :: getQuestionByCategoryAndDifficulty()");
        List<Question> questionList = this.questionRepository.findByCategoryAndDifficultyLevel(category, difficultyLevel);
        if (questionList.isEmpty())
            return new ResponseEntity<>(new Response(false, -1, "No question found with provided category : " + category + " & difficulty : " + difficultyLevel), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Response(true, 0, "Questions with category : " + category + " & difficulty : " + difficultyLevel + " fetched successfully", questionList), HttpStatus.OK);
    }

    @Override
    public void addQuestion(Question question) {
        log.info("QuestionService :: addQuestion()");
        this.questionRepository.save(question);
    }

    @Override
    public void addQuestions(List<Question> questionList) {
        log.info("QuestionService :: addQuestions()");
        this.questionRepository.saveAll(questionList);
    }

    @Override
    public ResponseEntity<Response> updateQuestion(Integer questionId, Question question) {
        log.info("QuestionService :: updateQuestion()");
        Optional<Question> oldQuestion = this.questionRepository.findById(questionId);
        if (oldQuestion.isPresent()) {
            if (Objects.nonNull(question.getCategory())) {
                oldQuestion.get().setCategory(question.getCategory());
            }
            if (Objects.nonNull(question.getQuestionTitle())) {
                oldQuestion.get().setQuestionTitle(question.getQuestionTitle());
            }
            if (Objects.nonNull(question.getDifficultyLevel())) {
                oldQuestion.get().setDifficultyLevel(question.getDifficultyLevel());
            }
            if (Objects.nonNull(question.getRightAnswer())) {
                oldQuestion.get().setRightAnswer(question.getRightAnswer());
            }
            if (Objects.nonNull(question.getOption1())) {
                oldQuestion.get().setOption1(question.getOption1());
            }
            if (Objects.nonNull(question.getOption2())) {
                oldQuestion.get().setOption2(question.getOption2());
            }
            if (Objects.nonNull(question.getOption3())) {
                oldQuestion.get().setOption3(question.getOption3());
            }
            if (Objects.nonNull(question.getOption4())) {
                oldQuestion.get().setOption4(question.getOption4());
            }
            this.questionRepository.save(oldQuestion.get());
            return new ResponseEntity<>(new Response(true, 0, "Question with id : " + questionId + " updated successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Response(false, -1, "Question with id : " + questionId + " not present"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> deleteQuestionById(Integer id) {
        log.info("QuestionService :: deleteQuestionById()");
        List<Question> questionList = this.questionRepository.findAll();
        boolean isPresent = questionList.stream().anyMatch(i -> i.getQuestionId() == id);
        if (isPresent) {
            this.questionRepository.deleteById(id);
            return new ResponseEntity<>(new Response(true, 0, "Question with ID : " + id + " deleted successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Response(false, -1, "Question with ID : " + id + " not present in records"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> searchQuestionByTitle(String questionTitle) {
        log.info("QuestionService :: searchQuestionByTitle()");
        List<Question> questionList = this.questionRepository.findByQuestionTitleContaining(questionTitle);
        if (questionList.isEmpty())
            return new ResponseEntity<>(new Response(false, -1, "No question found with provided question title : " + questionTitle), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Response(true, 0, "Questions with question title : " + questionTitle + " fetched successfully", questionList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getQuestionsForQuiz(String category, Integer numQ) {
        log.info("QuestionService :: getQuestionsForQuiz()");
        List<Integer> questionList = this.questionRepository.findRandomQuestionsByCategory(category, numQ).stream().map(Question::getQuestionId).collect(Collectors.toList());
        return new ResponseEntity<>(new Response(true, 0, "Question IDs list fetched successfully for provided category : " + category, questionList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getQuestionsFromId(List<Integer> questionIds) {
        log.info("QuestionService :: getQuestionsFromId()");
        List<QuestionWrapper> questionList = questionIds.stream().map(q ->
        {
            Question question = this.questionRepository.findById(q).get();
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setQuestionId(question.getQuestionId());
            questionWrapper.setQuestionTitle(question.getQuestionTitle());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            return questionWrapper;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new Response(true, 0, "Questions list fetched successfully", questionList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getScore(List<Answer> answerList) {
        log.info("QuestionService :: getScore()");
        long score = answerList.stream().filter(a -> this.questionRepository.findById(a.getQuestionId()).get().getRightAnswer().equalsIgnoreCase(a.getResponse())).count();
        return new ResponseEntity<>(new Response(true, 0, "Score : " + score), HttpStatus.OK);
    }

}
