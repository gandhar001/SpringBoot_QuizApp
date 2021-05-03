package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class QuestionDTO {
    private String question;

	
	private String description;
	
	
	private String questionType;
	
	
	
	
	private String questionScore;

	
	private List<String> quizAnswers;

	 
	private List<String> quizOptions;


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


	public List<String> getQuizAnswers() {
		return quizAnswers;
	}


	public void setQuizAnswers(List<String> quizAnswers) {
		this.quizAnswers = quizAnswers;
	}


	public List<String> getQuizOptions() {
		return quizOptions;
	}


	public void setQuizOptions(List<String> quizOptions) {
		this.quizOptions = quizOptions;
	}
	
}
