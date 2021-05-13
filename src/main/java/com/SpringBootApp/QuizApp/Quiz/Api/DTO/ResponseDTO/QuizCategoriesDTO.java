package com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class QuizCategoriesDTO {

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

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

	public String getQuizCount() {
		return quizCount;
	}

	public void setQuizCount(String quizCount) {
		this.quizCount = quizCount;
	}

	private long categoryId;

	private String category;

	private String description;

	private String quizCount;

	public QuizCategoriesDTO(long categoryId, String category, String description, String quizCount) {

		this.categoryId = categoryId;
		this.category = category;
		this.description = description;
		this.quizCount = quizCount;
	}

}
