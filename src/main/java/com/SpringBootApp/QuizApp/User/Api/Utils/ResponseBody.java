package com.SpringBootApp.QuizApp.User.Api.Utils;


public class ResponseBody<T> {
	
	T resBody=null;

	public T getResBody() {
		return resBody;
	}

	public void setResBody(T resBody) {
		this.resBody = resBody;
	}

	public ResponseBody(T resBody) {
		
		this.resBody = resBody;
	}
	
	

}
