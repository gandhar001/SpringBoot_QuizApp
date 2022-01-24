package com.SpringBootApp.QuizApp.Quiz.Api.Entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.SpringBootApp.QuizApp.User.Api.Entities.UserEntity;

@Entity

public class AttemptedQuizes implements Serializable {

	
	private static final long serialVersionUID = -4342662754931465675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attemptedQuizId;

	@OneToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private UserEntity user;

	@OneToOne(targetEntity = Quiz.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "quizId")
	private Quiz quiz;

	@Column(precision = 10, scale = 0)
	private double totalAttempts;

	@Column(precision = 10, scale = 0)
	private double percentageScored;

	@Column(precision = 10, scale = 0)
	private double marksScored;

	private Boolean attemptStatus;

	public long getAttemptedQuizId() {
		return attemptedQuizId;
	}

	public void setAttemptedQuizId(long attemptedQuizId) {
		this.attemptedQuizId = attemptedQuizId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public double getTotalAttempts() {
		return totalAttempts;
	}

	public void setTotalAttempts(Double totalAttempts) {
		this.totalAttempts = totalAttempts;
	}

	public double getPercentageScored() {
		return percentageScored;
	}

	public void setPercentageScored(Double percentageScored) {
		this.percentageScored = percentageScored;
	}

	public double getMarksScored() {
		return marksScored;
	}

	public void setMarksScored(Double marksScored) {
		this.marksScored = marksScored;
	}

	public Boolean getAttemptStatus() {
		return attemptStatus;
	}

	public void setAttemptStatus(Boolean attemptStatus) {
		this.attemptStatus = attemptStatus;
	}

	public AttemptedQuizes() {
		
	}

	public AttemptedQuizes(UserEntity user, Quiz quiz, double totalAttempts, double percentageScored,
			double marksScored, Boolean attemptStatus) {

		this.user = user;
		this.quiz = quiz;
		this.totalAttempts = totalAttempts;
		this.percentageScored = percentageScored;
		this.marksScored = marksScored;
		this.attemptStatus = attemptStatus;
	}

}
