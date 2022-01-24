package com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses;

public class UnableToSaveException extends GenericServiceException{
	
	private static final long serialVersionUID = -724392079538108238L;

	public UnableToSaveException( String message) {
		super( message);
		
	}
	
}
