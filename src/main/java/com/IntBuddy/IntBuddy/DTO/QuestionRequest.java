package com.IntBuddy.IntBuddy.DTO;

import java.util.List;

public class QuestionRequest {
	
	  private Long experienceId;

	    private List<QuestionDTO> questions;

		public Long getExperienceId() {
			return experienceId;
		}

		public void setExperienceId(Long experienceId) {
			this.experienceId = experienceId;
		}

		public List<QuestionDTO> getQuestions() {
			return questions;
		}

		public void setQuestions(List<QuestionDTO> questions) {
			this.questions = questions;
		}
	    
	    
	    


}
