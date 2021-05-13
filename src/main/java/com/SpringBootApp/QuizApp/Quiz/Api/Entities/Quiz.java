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
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quizId;

	@Column(nullable = false, unique = true)
	private String quizName;

	private String description;

	private String allocatedPoints;
	private String allocatedTime;
	private String totalQuestions;
	private String maxScore;

	@ManyToOne(targetEntity = QuizCategory.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private QuizCategory quizCategory;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	@OneToMany(targetEntity = QuizQuestion.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "quizId")
	private List<QuizQuestion> quizQuestions;

	public Quiz(String quizName, String description, String allocatedPoints, String allocatedTime,
			String totalQuestions, String maxScore, List<QuizQuestion> quizQuestions) {

		this.quizName = quizName;
		this.description = description;
		this.allocatedPoints = allocatedPoints;
		this.allocatedTime = allocatedTime;
		this.totalQuestions = totalQuestions;
		this.maxScore = maxScore;
		this.quizQuestions = quizQuestions;
	}

	public Quiz() {

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

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getDescription() {
		return description;
	}

	public List<QuizQuestion> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAllocatedPoints() {
		return allocatedPoints;
	}

	public void setAllocatedPoints(String allocatedPoints) {
		this.allocatedPoints = allocatedPoints;
	}

	public String getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

}
