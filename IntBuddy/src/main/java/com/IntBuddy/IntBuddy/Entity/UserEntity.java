package com.IntBuddy.IntBuddy.Entity;

import java.io.Serializable;
import java.util.List;


import com.IntBuddy.IntBuddy.Enum.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

	@Column(nullable = false, length = 30)
	private String name;

	private String email;

	private String phoneno;

	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	
	
	private String country;
	
	private String state;
	
	
	
	@OneToMany(mappedBy = "user")
	private List<ExperianceEntity> experiance;

	@OneToMany(mappedBy = "user")
	private List<CommentEntity> comment;

	// getter setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public List<CommentEntity> getComment() {
		return comment;
	}

	public void setComment(List<CommentEntity> comment) {
		this.comment = comment;
	}

	public void setExperiance(List<ExperianceEntity> experiance) {
		
		this.experiance = experiance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ExperianceEntity> getExperiance() {
		return experiance;
	}

	public void setExperiance1(List<ExperianceEntity> role) {
		this.experiance = (List<ExperianceEntity>) role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
}
