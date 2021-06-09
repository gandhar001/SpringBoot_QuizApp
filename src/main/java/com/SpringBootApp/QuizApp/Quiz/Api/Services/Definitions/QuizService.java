package com.SpringBootApp.QuizApp.Quiz.Api.Services.Definitions;

import java.util.Map;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizSubmissionDTO;

public interface QuizService {
	public Map<String, Object> createQuiz(CreateQuizDTO createQuizDTO) throws Exception;

	public Map<String, Object> fetchCategories() throws Exception;

	public Map<String, Object> fetchQuizes(Long categoryId) throws Exception;

	public Map<String, Object> fetchQuizQuestions(Long quizId) throws Exception;

	public Map<String, Object> generateQuizResult(QuizSubmissionDTO quizSubmissionDTO, Long quizId, Long categoryId)
			throws Exception;

}
