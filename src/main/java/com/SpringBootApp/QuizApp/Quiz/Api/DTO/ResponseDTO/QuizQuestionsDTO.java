package com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO;

import java.util.List;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizOptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuizQuestionsDTO {

	private String questionId;

	private String question;

	private String description;

	private String questionType;

	private String totalOptions;

	private String questionScore;

	private List<QuizOptions> quizOptions;

	public QuizQuestionsDTO(long questionId, String question, String description, String questionType,
			double totalOptions, double questionScore, List<QuizOptions> quizOptions) {

		this.questionId = String.valueOf(questionId);
		this.question = question;
		this.description = description;
		this.questionType = questionType;
		this.totalOptions = String.valueOf(totalOptions);
		this.questionScore = String.valueOf(questionScore);

		this.quizOptions = quizOptions;

	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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

	public String getTotalOptions() {
		return totalOptions;
	}

	public void setTotalOptions(String totalOptions) {
		this.totalOptions = totalOptions;
	}

	public String getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(String questionScore) {
		this.questionScore = questionScore;
	}

	public List<QuizOptions> getQuizOptions() {
		return quizOptions;
	}

	QuizQuestionsDTO() {

	}

}
