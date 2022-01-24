package com.SpringBootApp.QuizApp.Quiz.Api.Services.Definitions;

import java.util.Map;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizSubmissionDTO;

public interface QuizService {
	public Map<String, Object> createQuiz(CreateQuizDTO createQuizDTO) throws Exception;

	public Map<String, Object> searchCategory(String categoryKey,Integer pageNo, Integer pageSize, String sortBy) throws Exception;

	public Map<String, Object> searchQuiz(String quizKey,Integer pageNo, Integer pageSize, String sortBy) throws Exception;
	
	public Map<String, Object> fetchQuizesWithCategory( String categoryId,Integer pageNo, Integer pageSize, String sortBy) throws Exception;

	public Map<String, Object> fetchQuizQuestions(String quizId) throws Exception;

	public Map<String, Object> generateQuizResult(QuizSubmissionDTO quizSubmissionDTO, String quizId) throws Exception;

}
