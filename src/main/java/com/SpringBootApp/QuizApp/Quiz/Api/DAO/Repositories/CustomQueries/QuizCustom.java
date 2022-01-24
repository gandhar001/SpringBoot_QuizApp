package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.CustomQueries;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO;


public interface QuizCustom {
	List<QuizResDTO> searchQuiz(String quizKey, Pageable paging);
	List<QuizResDTO> fetchQuizesWithCategory(long categoryId, Pageable paging);
}
