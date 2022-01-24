package com.SpringBootApp.QuizApp.User.Api.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.Email;


@Entity
@Table(name="UserEntity")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 4799552540694629220L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	
	@Column(nullable = false)
	private String username;

	
	@Column(nullable = false)
	private String password;

	
	@Column(nullable = false)
	private String firstName;

	private String lastName;

	@Column(nullable = false)
	
	@Email(message="invalid email format.")
	private String email;

	@Column(precision = 10, scale = 2, columnDefinition = "Decimal(10,2) default '0.00'")
	private Double proFactor = 0.0;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	public Double getProFactor() {
		return proFactor;
	}

	public void setProFactor(Double proFactor) {
		this.proFactor = proFactor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserEntity() {

	}

	public UserEntity(String username, String firstName, String lastName, String email, String password) {

		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
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

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
