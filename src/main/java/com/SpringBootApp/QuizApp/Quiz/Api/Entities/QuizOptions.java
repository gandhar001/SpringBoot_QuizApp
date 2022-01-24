package com.SpringBootApp.QuizApp.Quiz.Api.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class QuizOptions implements Serializable {

	private static final long serialVersionUID = -8545784074972365526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long optionId;

	@Column(nullable = false)
	private String quizOption;

	@JsonIgnore
	private Boolean isAnswer;

	@ManyToOne(targetEntity = QuizQuestion.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "questionId", insertable = false, updatable = false)
	private QuizQuestion quizQuestion;

	public String getQuizOption() {
		return quizOption;
	}

	public void setQuizOption(String quizOption) {
		this.quizOption = quizOption;
	}

	public QuizOptions(String quizOption, Boolean isAnswer) {
		super();
		this.quizOption = quizOption;
		this.isAnswer = isAnswer;
	}

	public Boolean getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(Boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public QuizOptions() {

	}

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
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
