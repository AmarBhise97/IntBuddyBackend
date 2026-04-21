package com.IntBuddy.IntBuddy.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.*;

@Entity
public class ExperianceEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long experiance_ID;

	@ManyToOne
	@JoinColumn(name = "id")
	private UserEntity user;

	@OneToMany()
	private List<CommentEntity> comments;

	private String companyName;

	private String position;

	private LocalDateTime date;

	private String details;

	private boolean result;

	private String role;
	
	private String experianceinyear;
	

	public String getExperianceinyear() {
		return experianceinyear;
	}

	public void setExperianceinyear(String experianceinyear) {
		this.experianceinyear = experianceinyear;
	}

	// getters & setters
	public Long getExperiance_ID() {
		return experiance_ID;
	}

	public void setExperiance_ID(Long experiance_ID) {
		this.experiance_ID = experiance_ID;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isResult() {
		return result;

	}

	public void setResult(boolean result) {
		this.result = result;

	}

	public String getRole() {
		return role;

	}

	public void setRole(String role) {
		this.role = role;
	}
	public List<CommentEntity> getComments() {
		return comments;
	}
	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

}