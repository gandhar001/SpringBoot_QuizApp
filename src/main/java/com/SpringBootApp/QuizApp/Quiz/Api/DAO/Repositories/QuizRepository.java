package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.CustomQueries.QuizCustom;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizCustom {

	@Query("SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO"
			+ "(qz.quizId,qz.quizName, qz.description,qz.allocatedPoints,qz.allocatedTime,qz.totalQuestions,qz.maxScore,"
			+ "qz.passingPercentage) " + "from Quiz qz where qz.quizCategory.categoryId =:categoryId")
	List<QuizResDTO> fetchQuizes(@Param("categoryId") long categoryId);
//
//	@Query("SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO(qz.quizId,qz.quizName, qz.description,qz.allocatedPoints,qz.allocatedTime,qz.totalQuestions,qz.maxScore,qz.passingPercentage) from Quiz qz where qz.quizCategory.categoryId =:categoryId and qz.quizName like %:quizKey% ")
//	List<QuizResDTO> searchQuiz(@Param("quizKey") String quizKey,@Param("categoryId")long categoryId);

}
