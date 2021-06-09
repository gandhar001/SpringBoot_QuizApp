package com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO;

public class GeneratedQuizResultDTO {

	private String percentageScored;

	private String attemptedQuestions;
	private String correctAnswers;
	private String incorrectAnswers;
	private String marksScored;
	private String attemptStatus;
	private String pointsAchieved;

	public GeneratedQuizResultDTO(String percentageScored, String attemptedQuestions, String correctAnswers,
			String incorrectAnswers, String marksScored, String attemptStatus, String pointsAchieved) {

		this.percentageScored = percentageScored;
		this.attemptedQuestions = attemptedQuestions;
		this.correctAnswers = correctAnswers;
		this.incorrectAnswers = incorrectAnswers;
		this.marksScored = marksScored;
		this.attemptStatus = attemptStatus;
		this.pointsAchieved = pointsAchieved;
	}

	public String getPercentageScored() {
		return percentageScored;
	}

	public void setPercentageScored(String percentageScored) {
		this.percentageScored = percentageScored;
	}

	public String getAttemptedQuestions() {
		return attemptedQuestions;
	}

	public void setAttemptedQuestions(String attemptedQuestions) {
		this.attemptedQuestions = attemptedQuestions;
	}

	public String getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(String correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public String getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(String incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	public String getMarksScored() {
		return marksScored;
	}

	public void setMarksScored(String marksScored) {
		this.marksScored = marksScored;
	}

	public String getAttemptStatus() {
		return attemptStatus;
	}

	public void setAttemptStatus(String attemptStatus) {
		this.attemptStatus = attemptStatus;
	}

	public String getPointsAchieved() {
		return pointsAchieved;
	}

	public void setPointsAchieved(String pointsAchieved) {
		this.pointsAchieved = pointsAchieved;
	}

}
