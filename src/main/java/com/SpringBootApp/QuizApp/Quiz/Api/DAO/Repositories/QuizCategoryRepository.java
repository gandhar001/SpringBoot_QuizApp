package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.CustomQueries.QuizCategoryCustom;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoryDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizCategory;

@Repository
public interface QuizCategoryRepository extends JpaRepository<QuizCategory, Long>, QuizCategoryCustom {

	@Query("SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoryDTO(qc.categoryId,qc.category,qc.description,qc.quizCount) from QuizCategory qc")
	List<QuizCategoryDTO> fetchCategories();
	
	@Query("SELECT qc from QuizCategory qc where qc.category =:category")
    QuizCategory findByCategory(@Param("category") String category);

//	@Query("SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoriesDTO(qc.categoryId,qc.category,qc.description,qc.quizCount)"
//			+ " from QuizCategory qc where qc.category like %:categoryKey% ")
//	List<QuizCategoriesDTO> searchCategory(@Param("categoryKey") String categoryKey);

}
