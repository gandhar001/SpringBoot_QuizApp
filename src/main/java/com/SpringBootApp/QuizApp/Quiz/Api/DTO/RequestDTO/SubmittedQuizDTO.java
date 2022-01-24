package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

import javax.validation.constraints.NotNull;

public class SubmittedQuizDTO {
	
@NotNull
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
