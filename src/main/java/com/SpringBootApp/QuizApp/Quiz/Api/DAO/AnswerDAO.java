package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizAnswers;



@Repository
public interface AnswerDAO extends CrudRepository<QuizAnswers,Long> {
	
	 


}
