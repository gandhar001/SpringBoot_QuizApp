package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoriesDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizCategory;

@Repository
public interface QuizCategoryDAO extends CrudRepository<QuizCategory, Long> {

	@Query("SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoriesDTO(qc.categoryId,qc.category,qc.description,qc.quizCount) from QuizCategory qc")
	List<QuizCategoriesDTO> fetchCategories();

}
