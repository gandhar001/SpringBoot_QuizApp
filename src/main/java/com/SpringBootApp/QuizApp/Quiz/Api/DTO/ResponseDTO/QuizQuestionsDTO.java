package com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO;

import java.util.Date;
import java.util.List;


import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizOptions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuizQuestionsDTO {

	private long questionId;

	private String question;

	private String description;

	private String questionType;

	private String totalOptions;

	private String questionScore;

	private List<QuizOptions> quizOptions;

	public QuizQuestionsDTO(long questionId, String question, String description, String questionType,
			String totalOptions, String questionScore, List<QuizOptions> quizOptions, Date createdAt,
			Date updatedAt) {

		this.questionId = questionId;
		this.question = question;
		this.description = description;
		this.questionType = questionType;
		this.totalOptions = totalOptions;
		this.questionScore = questionScore;
		this.quizOptions = quizOptions;

		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	private Date createdAt;

	private Date updatedAt;

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
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

	public void setQuizOptions(List<QuizOptions> quizOptions) {
		this.quizOptions = quizOptions;
	}

	public QuizQuestionsDTO() {

	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
