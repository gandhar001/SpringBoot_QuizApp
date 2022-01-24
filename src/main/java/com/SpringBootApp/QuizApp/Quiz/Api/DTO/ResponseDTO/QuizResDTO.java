package com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuizResDTO {

	private String quizId;
	private QuizCategory category;
	private String quizName;
	private String description;
	private String allocatedPoints;
	private String allocatedTime;
	private String passingPercentage;
	private String totalQuestions;
	private String maxScore;

	public QuizResDTO(long quizId, String quizName, String description, double allocatedPoints, double allocatedTime,
			double totalQuestions, double maxScore, double passingPercentage, QuizCategory category) {
		this.quizId = String.valueOf(quizId);
		this.quizName = quizName;
		this.description = description;
		this.allocatedPoints = String.valueOf(allocatedPoints);
		this.allocatedTime = String.valueOf(allocatedTime);
		this.totalQuestions = String.valueOf(totalQuestions);
		this.maxScore = String.valueOf(maxScore);
		this.category = category;
		this.passingPercentage = String.valueOf(passingPercentage);

	}

	public QuizResDTO(long quizId, String quizName, String description, double allocatedPoints, double allocatedTime,
			double totalQuestions, double maxScore, double passingPercentage) {
		this.quizId = String.valueOf(quizId);
		this.quizName = quizName;
		this.description = description;
		this.allocatedPoints = String.valueOf(allocatedPoints);
		this.allocatedTime = String.valueOf(allocatedTime);
		this.totalQuestions = String.valueOf(totalQuestions);
		this.maxScore = String.valueOf(maxScore);

		this.passingPercentage = String.valueOf(passingPercentage);

	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public QuizCategory  getCategory() {
		return category;
	}

	public void setCategory(QuizCategory  category) {
		this.category = category;
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

	public String getPassingPercentage() {
		return passingPercentage;
	}

	public void setPassingPercentage(String passingPercentage) {
		this.passingPercentage = passingPercentage;
	}

}
