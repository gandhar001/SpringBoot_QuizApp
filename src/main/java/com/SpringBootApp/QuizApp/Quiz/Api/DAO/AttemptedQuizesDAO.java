package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.AttemptedQuizes;


@Repository
public interface AttemptedQuizesDAO extends CrudRepository<AttemptedQuizes, Long> {
	@Query("SELECT aq from AttemptedQuizes aq where aq.quiz.quizId =:quizId ")
	AttemptedQuizes fetchAttemptedQuiz(@Param("quizId") Long quizId);
}
