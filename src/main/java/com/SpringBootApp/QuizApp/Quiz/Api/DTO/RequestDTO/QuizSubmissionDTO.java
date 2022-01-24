package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class QuizSubmissionDTO {

	@NotEmpty(message = "userId can't be null or empty.")
	private String userId;

	@NotEmpty(message = "submittedQuiz can't be null or empty.")
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
