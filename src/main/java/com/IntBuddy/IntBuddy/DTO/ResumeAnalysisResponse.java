package com.IntBuddy.IntBuddy.DTO;

import java.util.List;

public class ResumeAnalysisResponse {
	
	
	  private int score;

	    private List<String> suggestions;

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }

	    public List<String> getSuggestions() {
	        return suggestions;
	    }

	    public void setSuggestions(List<String> suggestions) {
	        this.suggestions = suggestions;
	    }

}
