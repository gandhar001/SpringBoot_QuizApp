package com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses;

public class ResourceNotFoundException extends  GenericServiceException {

	private static final long serialVersionUID = -8875811771221027729L;

	public ResourceNotFoundException(String resourceType, String requestedResource, String message) {
		super(resourceType,requestedResource,message);
		
	}
	
	public ResourceNotFoundException( String message) {
		super(message);
		
	}

	
}
