package com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses;

public class DatabaseException extends GenericServiceException {

	public DatabaseException(String resourceType, String resource, String message) {
		super(resourceType, resource, message);

	}

	public DatabaseException(String message) {
		super(message);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1066210389409427250L;

}
