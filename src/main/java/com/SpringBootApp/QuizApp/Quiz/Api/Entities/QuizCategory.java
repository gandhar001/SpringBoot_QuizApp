package com.SpringBootApp.QuizApp.Quiz.Api.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "quizes", "createdAt", "updatedAt" })
@Entity
public class QuizCategory implements Serializable {

	private static final long serialVersionUID = -5676395399797221577L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;

	@Column(nullable = false)
	private String category;

	private String description;

	private String quizCount;

	@OneToMany(targetEntity = Quiz.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	private List<Quiz> quizes;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public QuizCategory() {

	}

	public QuizCategory(String category, String description, String quizCount, List<Quiz> quizes) {

		this.category = category;
		this.description = description;
		this.quizCount = quizCount;
		this.quizes = quizes;

	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
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

	public String getQuizCount() {
		return quizCount;
	}

	public void setQuizCount(String quizCount) {
		this.quizCount = quizCount;
	}

}
