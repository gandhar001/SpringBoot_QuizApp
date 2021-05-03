package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class CreateQuizDTO {
	
	
	
	
	private List<CategoryDTO> quizCategories ;

	public List<CategoryDTO> getQuizCategories() {
		return quizCategories;
	}

	public void setQuizCategories(List<CategoryDTO> quizCategories) {
		this.quizCategories = quizCategories;
	}
	


	

}
