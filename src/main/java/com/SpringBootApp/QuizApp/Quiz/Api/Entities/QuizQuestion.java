package com.SpringBootApp.QuizApp.Quiz.Api.Entities;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class QuizQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionId;

	@Column(nullable = false, unique = true)
	private String question;

	private String description;

	private String questionType;

	private String totalOptions;

	private String totalAnswers;

	public QuizQuestion() {
	
	}

	private String questionScore;

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

	

	public QuizQuestion(String question, String description, String questionType, String totalOptions,
			String totalAnswers, String questionScore,List<QuizOptions> quizOptions) {

		this.question = question;
		this.description = description;
		this.questionType = questionType;
		this.totalOptions = totalOptions;
		this.totalAnswers = totalAnswers;
		this.questionScore = questionScore;
	
		this.quizOptions = quizOptions;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	public String getTotalOptions() {
		return totalOptions;
	}

	public void setTotalOptions(String totalOptions) {
		this.totalOptions = totalOptions;
	}

	public String getTotalAnswers() {
		return totalAnswers;
	}

	public void setTotalAnswers(String totalAnswers) {
		this.totalAnswers = totalAnswers;
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

	public String getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(String questionScore) {
		this.questionScore = questionScore;
	}

	
	public List<QuizOptions> getOptions() {
		return quizOptions;
	}

	public void setOptions(List<QuizOptions> quizOptions) {
		this.quizOptions = quizOptions;
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
