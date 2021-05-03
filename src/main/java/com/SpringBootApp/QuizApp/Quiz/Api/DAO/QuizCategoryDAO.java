package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizCategory;


@Repository
public interface QuizCategoryDAO extends CrudRepository<QuizCategory,Long> {
	
	 


}
