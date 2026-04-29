package com.IntBuddy.IntBuddy.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ExperianceDTO implements Serializable {

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

	private String fullName;

	private List<Comment3DTO> coment;

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

	public Long getExperiance_ID() {
		return experiance_ID;
	}

	public void setExperiance_ID(Long experiance_ID) {
		this.experiance_ID = experiance_ID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Comment3DTO> getComent() {
		return coment;
	}

	public void setComent(List<Comment3DTO> coment) {
		this.coment = coment;
	}

}
