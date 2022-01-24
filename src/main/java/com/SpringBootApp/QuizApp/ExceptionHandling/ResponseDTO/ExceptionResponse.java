package com.SpringBootApp.QuizApp.ExceptionHandling.ResponseDTO;

import java.time.LocalDateTime;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp = LocalDateTime.now();
	private String error;
	private Map<String, Object> responseBody;
	private String message;
	private String requestPath;
	 private String responseStatus = "failed";

	 public ExceptionResponse(Map<String, Object> responseBody, String error, String message, String requestPath) {
			this.responseBody = responseBody;
			this.error = error;
			this.requestPath = requestPath;
			this.message = message;
			
		}
	public ExceptionResponse(Map<String, Object> responseBody, String error, String message, String requestPath,String responseStatus) {
		this.responseBody = responseBody;
		this.error = error;
		this.requestPath = requestPath;
		this.message = message;
		this.responseStatus=responseStatus;
	}

	public ExceptionResponse(String error, String message, String requestPath,String responseStatus) {

		this.error = error;
		this.requestPath = requestPath;
		this.message = message;
		this.responseStatus=responseStatus;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public Map<String, Object> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Map<String, Object> responseBody) {
		this.responseBody = responseBody;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
