package com.SpringBootApp.QuizApp.Quiz.Api.Services.Definitions;


import java.util.Map;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;


public interface QuizService {
	public Map<String,Object> createQuiz(CreateQuizDTO createQuizDTO) throws Exception;
	
	
}
