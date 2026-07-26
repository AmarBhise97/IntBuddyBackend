package com.IntBuddy.IntBuddy.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="interview_questions")
public class InterviewQuestionEntity {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String question;

	    @Column(length=5000)
	    private String answer;

	    @ManyToOne
	    @JoinColumn(name="experience_id")
	    private AnonymousExperienceEntity experience;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public AnonymousExperienceEntity getExperience() {
			return experience;
		}

		public void setExperience(AnonymousExperienceEntity experience) {
			this.experience = experience;
		}
	    
	    

}
