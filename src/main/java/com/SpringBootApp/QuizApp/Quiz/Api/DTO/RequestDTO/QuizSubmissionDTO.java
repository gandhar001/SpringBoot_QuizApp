package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class QuizSubmissionDTO {
	private String userId;


	List<SubmittedQuizDTO> submittedQuiz;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SubmittedQuizDTO> getSubmittedQuiz() {
		return submittedQuiz;
	}

	public void setSubmittedQuiz(List<SubmittedQuizDTO> submittedQuiz) {
		this.submittedQuiz = submittedQuiz;
	}

	
}


