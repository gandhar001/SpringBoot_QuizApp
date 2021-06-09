package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class SubmittedQuizDTO {
	private String questionId;

	List<String> attemptedOptions;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public List<String> getAttemptedOptions() {
		return attemptedOptions;
	}

	public void setAttemptedOptions(List<String> attemptedOptions) {
		this.attemptedOptions = attemptedOptions;
	}
}
