package com.SpringBootApp.QuizApp.Quiz.Api.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.AttemptedQuizes;

@Repository
public interface AttemptedQuizesDAO extends CrudRepository<AttemptedQuizes, Long> {

}
