package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class QuestionDTO {
	private String question;

	private String description;

	private String questionType;

	private String questionScore;

	private List<QuizOptionsDTO> quizOptions;

	public List<QuizOptionsDTO> getQuizOptions() {
		return quizOptions;
	}

	public void setQuizOptions(List<QuizOptionsDTO> quizOptions) {
		this.quizOptions = quizOptions;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(String questionScore) {
		this.questionScore = questionScore;
	}

}
