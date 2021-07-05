package com.SpringBootApp.QuizApp.Utils.ResponseDTO;



import java.time.LocalDateTime;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResDTO {
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime  responseTime;
	private Map<String,Object> responseBody;
	private String status;
	private HttpStatus httpStatus;
	
	

	public ResDTO(Map<String,Object> responseBody, String status, HttpStatus httpStatus, LocalDateTime responseTime ) {
		
		this.responseBody = responseBody;
		this.status = status;
		this.httpStatus = httpStatus;
		
		this.responseTime = responseTime;
	}


	public Map<String,Object>  getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Map<String,Object>  responseBody) {
		this.responseBody = responseBody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	


	public LocalDateTime getResponseTime() {
		return responseTime;
	}


	public void setResponseTime(LocalDateTime responseTime) {
		this.responseTime = responseTime;
	}

	
}
