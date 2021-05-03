package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.Quiz;


@Repository
public interface QuizDAO extends CrudRepository<Quiz,Long> {
	
	 


}
