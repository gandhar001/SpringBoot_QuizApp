package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.CustomQueries;



import java.util.List;


import org.springframework.data.domain.Pageable;


import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoryDTO;



public interface QuizCategoryCustom {
	List<QuizCategoryDTO> searchCategory(String categoryKey,Pageable paging);
	
}
