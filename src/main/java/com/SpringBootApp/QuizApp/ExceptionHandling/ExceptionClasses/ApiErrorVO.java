package com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class ApiErrorVO {
 
	
    private String errorCode="VALIDATION_ERROR";
	
	
    private String fieldName;
    
    private String message;
 
    public ApiErrorVO( String message,String fieldName) {
     
        this.message = message;
        this.fieldName=fieldName;
    }

	public String getErrorCode() {
		return errorCode;
	}


	public String getFieldName() {
		return fieldName;
	}

	

	public String getMessage() {
		return message;
	}

	
    
    
    
}