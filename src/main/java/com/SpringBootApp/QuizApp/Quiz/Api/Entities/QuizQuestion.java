package com.SpringBootApp.QuizApp.Quiz.Api.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.SubmittedQuizDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class QuizQuestion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1459856747703775628L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionId;

	@Column(nullable = false, unique = true)
	private String question;

	@Column(nullable = false)
	private Double questionScore;

	@ManyToOne(targetEntity = Quiz.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "quizId", insertable = false, updatable = false)
	private Quiz quiz;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "questionId")

	private List<QuizOptions> quizOptions;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	private String description;

	@Column(nullable = false)
	private String questionType;

	private Double totalOptions;

	private Double totalAnswers;

	public Double getTotalOptions() {
		return totalOptions;
	}

	public void setTotalOptions(Double totalOptions) {
		this.totalOptions = totalOptions;
	}

	public Double getTotalAnswers() {
		return totalAnswers;
	}

	public void setTotalAnswers(Double totalAnswers) {
		this.totalAnswers = totalAnswers;
	}

	public Double getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(Double questionScore) {
		this.questionScore = questionScore;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public QuizQuestion(String question, String description, String questionType, Double totalOptions,
			Double totalAnswers, Double questionScore, List<QuizOptions> quizOptions) {

		this.question = question;
		this.description = description;
		this.questionType = questionType;
		this.totalOptions = totalOptions;
		this.totalAnswers = totalAnswers;
		this.questionScore = questionScore;

		this.quizOptions = quizOptions;
	}

	

	public QuizQuestion() {

	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<QuizOptions> getQuizOptions() {
		return quizOptions;
	}

	public void setQuizOptions(List<QuizOptions> quizOptions) {
		this.quizOptions = quizOptions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
