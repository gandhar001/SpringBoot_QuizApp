package com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO;

import java.util.List;

public class CategoryDTO 
{
	private String category;
	private String description;
	private List<QuizDTO> quizes ;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<QuizDTO> getQuizes() {
		return quizes;
	}
	public void setQuizes(List<QuizDTO> quizes) {
		this.quizes = quizes;
	}
	
	
}
