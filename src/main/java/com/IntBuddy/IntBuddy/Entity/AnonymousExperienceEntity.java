package com.IntBuddy.IntBuddy.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="anonymous_experience")
public class AnonymousExperienceEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean anonymous;

    private String company;

    private String role;

    private boolean gotOffer;

    private String rejectReason;

    private String location;

    @OneToMany(mappedBy = "experience",
            cascade = CascadeType.ALL)
    private List<InterviewQuestionEntity> questions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isGotOffer() {
		return gotOffer;
	}

	public void setGotOffer(boolean gotOffer) {
		this.gotOffer = gotOffer;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<InterviewQuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(List<InterviewQuestionEntity> questions) {
		this.questions = questions;
	}
    

}
