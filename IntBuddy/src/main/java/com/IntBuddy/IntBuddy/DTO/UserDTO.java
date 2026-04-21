package com.IntBuddy.IntBuddy.DTO;

import java.io.Serializable;
import java.util.List;

import com.IntBuddy.IntBuddy.Enum.Gender;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	private String email;

	private String phoneno;

	private Gender gender;

	private String country;

	private String state;
	
	List<ExperianceDTO2> experiance;

	List<CommentDTO2> comment;
	
	

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

	public List<ExperianceDTO2> getExperiance() {
		return experiance;
	}

	public void setExperiance(List<ExperianceDTO2> experiance) {
		this.experiance = experiance;
	}

	public List<CommentDTO2> getComment() {
		return comment;
	}

	public void setComment(List<CommentDTO2> comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
