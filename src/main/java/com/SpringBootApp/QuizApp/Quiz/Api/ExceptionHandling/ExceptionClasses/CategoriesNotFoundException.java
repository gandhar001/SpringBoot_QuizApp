package com.SpringBootApp.QuizApp.Quiz.Api.ExceptionHandling.ExceptionClasses;

import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.GenericServiceException;

public class CategoriesNotFoundException extends GenericServiceException {


	private static final long serialVersionUID = 3803161496436158912L;

	public CategoriesNotFoundException(String message) {
		super(message);
	
	}

}
