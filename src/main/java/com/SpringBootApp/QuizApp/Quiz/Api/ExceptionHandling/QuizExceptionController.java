package com.SpringBootApp.QuizApp.Quiz.Api.ExceptionHandling;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.SpringBootApp.QuizApp.ExceptionHandling.ResponseDTO.ExceptionResponse;
import com.SpringBootApp.QuizApp.Quiz.Api.ExceptionHandling.ExceptionClasses.CategoriesNotFoundException;

@ControllerAdvice
public class QuizExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(QuizExceptionController.class);

	@ExceptionHandler(value = CategoriesNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleCategoriesNotFoundException(CategoriesNotFoundException ex,
			HttpServletRequest request) {
		logger.error("CategoriesNotFoundException Exception occured while making request", ex, request.getRequestURI());

		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(ex.getRequestedResource(), "CATEGORIES_NOT_FOUND",
				ex.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
	}
	
	
	
}


