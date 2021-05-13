package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.Quiz;

@Repository
public interface QuizDAO extends CrudRepository<Quiz, Long> {

	@Query("SELECT qz from Quiz qz where qz.quizCategory.categoryId =:categoryId")
	List<Quiz> fetchQuiz(@Param("categoryId") Long categoryId);

//	com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.FetchedQuizesDTO(qz.quizId,qz.quizName,"
//			+ "qz.description,qz.allocatedPoints,qz.allocatedTime,qz.totalQuestions,qz.maxScore,qz.createdAt,qz.updatedAt)
}
