package com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses;

import java.util.HashMap;
import java.util.Map;

public class GenericServiceException extends RuntimeException {

	public static final long serialVersionUID = 1L;

	public String message = "Generic Service failure occured.";

	public Map<String, Object> requestedResource;

	public GenericServiceException(String errorType, String error, String message) {
		this.requestedResource = new HashMap<String, Object>();
		requestedResource.put(errorType, error);
		this.message = message;

	}

	public GenericServiceException(String message) {

		this.message = message;

	}

	public Map<String, Object> getRequestedResource() {
		return requestedResource;
	}

	public String getMessage() {
		return message;
	}

}
