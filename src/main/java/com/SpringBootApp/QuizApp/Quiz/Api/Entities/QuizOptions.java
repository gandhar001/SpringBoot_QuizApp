package com.SpringBootApp.QuizApp.Quiz.Api.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class QuizOptions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long optionId;
	
	
	private String quizOption;
	

	public QuizOptions(String quizOption) {
		super();
		this.quizOption = quizOption;
	}
	public QuizOptions()
	{
		
	}

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
	private Date updatedAt;
	
	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public String getOption() {
		return quizOption;
	}

	public void setOption(String quizOption) {
		this.quizOption = quizOption;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	

}
