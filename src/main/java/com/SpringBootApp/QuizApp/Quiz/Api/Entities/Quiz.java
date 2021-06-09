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
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quizId;

	@Column(nullable = false, unique = true)
	private String quizName;

	private String description;

	@Column(precision = 10, scale = 0)
	private Double passingPercentage;

	@Column(precision = 10, scale = 0)
	private Double allocatedPoints;

	@Column(precision = 10, scale = 0)
	private Double allocatedTime;

	@Column(precision = 10, scale = 0)
	private Double totalQuestions;

	@Column(precision = 10, scale = 0)
	private Double maxScore;

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

	public Quiz(String quizName, String description, Double passingPercentage, Double allocatedPoints,
			Double allocatedTime, Double totalQuestions, Double maxScore, List<QuizQuestion> quizQuestions) {

		this.quizName = quizName;
		this.description = description;
		this.passingPercentage = passingPercentage;
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

	public Double getAllocatedPoints() {
		return allocatedPoints;
	}

	public void setAllocatedPoints(Double allocatedPoints) {
		this.allocatedPoints = allocatedPoints;
	}

	public Double getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(Double allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public Double getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Double totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	public QuizCategory getQuizCategory() {
		return quizCategory;
	}

	public void setQuizCategory(QuizCategory quizCategory) {
		this.quizCategory = quizCategory;
	}

	public Double getPassingPercentage() {
		return passingPercentage;
	}

	public void setPassingPercentage(Double passingPercentage) {
		this.passingPercentage = passingPercentage;
	}

}
