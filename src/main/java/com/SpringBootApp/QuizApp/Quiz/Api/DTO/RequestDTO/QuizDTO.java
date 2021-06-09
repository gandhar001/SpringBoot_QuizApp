package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class QuizDTO {
	private String quizName;
	private String description;
	private String allocatedPoints;
	private String allocatedTime;
	private String maxScore;
	

	private String passingPercentage;

	private List<QuestionDTO> questions;

	public QuizDTO() {

	}

	public QuizDTO(String quizName, String description, String allocatedPoints, String allocatedTime, String maxScore,
			String passingPercentage, List<QuestionDTO> questions) {

		this.quizName = quizName;
		this.description = description;
		this.allocatedPoints = allocatedPoints;
		this.allocatedTime = allocatedTime;
		this.maxScore = maxScore;
		this.passingPercentage = passingPercentage;
		this.questions = questions;
	}

	
	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
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

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getPassingPercentage() {
		return passingPercentage;
	}

	public void setPassingPercentage(String passingPercentage) {
		this.passingPercentage = passingPercentage;
	}

}
