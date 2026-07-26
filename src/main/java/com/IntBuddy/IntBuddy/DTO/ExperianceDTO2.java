package com.IntBuddy.IntBuddy.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExperianceDTO2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Long experiance_ID;


	private String companyName;

	private String position;

	private LocalDateTime date;

	private String details;

	private boolean result;
	
	private String role;

	private String experianceinyear;
	
private String resumeName;
	
	private String resumeType;

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

	public String getExperianceinyear() {
		return experianceinyear;
	}

	public void setExperianceinyear(String experianceinyear) {
		this.experianceinyear = experianceinyear;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getResumeType() {
		return resumeType;
	}

	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}

	public Long getExperiance_ID() {
		return experiance_ID;
	}

	public void setExperiance_ID(Long experiance_ID) {
		this.experiance_ID = experiance_ID;
	}
	
	
	

}
