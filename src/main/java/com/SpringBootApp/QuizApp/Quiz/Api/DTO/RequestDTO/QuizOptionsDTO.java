package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

public class QuizOptionsDTO {

	private String option;
	private Boolean isAnswer;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Boolean getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(Boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public QuizOptionsDTO(String option, Boolean isAnswer) {
		super();
		this.option = option;
		this.isAnswer = isAnswer;
	}

}
