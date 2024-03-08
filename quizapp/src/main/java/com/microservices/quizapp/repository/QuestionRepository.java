package com.microservices.quizapp.repository;

import com.microservices.quizapp.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);
    List<Question> findByCategoryAndDifficultyLevel(String category, String difficultyLevel);
    List<Question> findByQuestionTitleContaining(String questionTitle);
    @Query(value = "select * from question q where q.category =:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
