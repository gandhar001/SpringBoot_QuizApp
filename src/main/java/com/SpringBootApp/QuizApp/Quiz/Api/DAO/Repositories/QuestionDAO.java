package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizQuestion;

@Repository
public interface QuestionDAO extends CrudRepository<QuizQuestion, Long> {

	//@Query(value="SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizQuestionsDTO
	//(qs.questionId,qs.question,qs.description,qs.questionType,qs.totalOptions,qs.questionScore,qs.createdAt,qs.updatedAt,qs.quizOptions) from QuizQuestion qs where qs.quiz.quizId =:quizId ")
//	@Query("SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizQuestionsDTO"
//			+ "(qs.questionId,qs.question,qs.description,qs.questionType,qs.totalOptions,qs.questionScore,qs.createdAt,qs.updatedAt,qs.quizOptions) from QuizQuestion qs where qs.quiz.quizId =:quizId ")
	
	
	@Query("SELECT qs from QuizQuestion qs where qs.quiz.quizId =:quizId ")
	List<QuizQuestion> fetchQuizQuestions(@Param("quizId") long quizId);

	@Query("SELECT qs from QuizQuestion qs inner join qs.quiz qz where qz.quizId =:quizId and qs.questionId IN (:attemptedQuestions)")
	List<QuizQuestion> fetchAttemptedQuestions(@Param("attemptedQuestions") List<Long> attemptedQuestions,
			@Param("quizId") long quizId);

}
