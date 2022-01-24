package com.SpringBootApp.QuizApp.Utils.ResponseDTO;

import java.time.LocalDateTime;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResDTO {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime responseTime = LocalDateTime.now();
	private Map<String, Object> responseBody;
	private final String responseStatus = "success";

	public ResDTO(Map<String, Object> responseBody) {

		this.responseBody = responseBody;

	}

	public Map<String, Object> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Map<String, Object> responseBody) {
		this.responseBody = responseBody;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public LocalDateTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(LocalDateTime responseTime) {
		this.responseTime = responseTime;
	}

}
