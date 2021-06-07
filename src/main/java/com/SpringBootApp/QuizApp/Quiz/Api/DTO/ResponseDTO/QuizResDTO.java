package com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuizResDTO {

	private String quizId;
	private String quizName;
	private String description;
	private String allocatedPoints;
	private String allocatedTime;
	private String totalQuestions;
	private String maxScore;
	private Date createdAt;
	private Date updatedAt;

	public QuizResDTO(String quizId, String quizName, String description, String allocatedPoints, String allocatedTime,
			String totalQuestions, String maxScore, Date createdAt, Date updatedAt) {
		this.quizId = quizId;
		this.quizName = quizName;
		this.description = description;
		this.allocatedPoints = allocatedPoints;
		this.allocatedTime = allocatedTime;
		this.totalQuestions = totalQuestions;
		this.maxScore = maxScore;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAllocatedPoints() {
		return allocatedPoints;
	}

	public void setAllocatedPoints(String allocatedPoints) {
		this.allocatedPoints = allocatedPoints;
	}

	public String getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getQuizId() {
		return quizId;
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
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
