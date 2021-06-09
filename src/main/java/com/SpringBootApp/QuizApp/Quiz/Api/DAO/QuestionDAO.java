package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizQuestion;

@Repository
public interface QuestionDAO extends CrudRepository<QuizQuestion, Long> {

	@Query("SELECT qs from QuizQuestion qs where qs.quiz.quizId =:quizId ")
	List<QuizQuestion> fetchQuizQuestions(@Param("quizId") Long quizId);

	@Query("SELECT qs from QuizQuestion qs inner join qs.quiz qz where qz.quizId =:quizId and qs.questionId IN (:attemptedQuestions)")
	List<QuizQuestion> fetchAttemptedQuestions(@Param("attemptedQuestions") List<Long> attemptedQuestions,
			@Param("quizId") Long quizId);

}
