package com.IntBuddy.IntBuddy.DTO;

import java.util.List;

public class JDMatchResponse {

	
	  private int match;

	    private List<String> strengths;

	    private List<String> missingKeywords;

	    public int getMatch() {
	        return match;
	    }

	    public void setMatch(int match) {
	        this.match = match;
	    }

	    public List<String> getStrengths() {
	        return strengths;
	    }

	    public void setStrengths(List<String> strengths) {
	        this.strengths = strengths;
	    }

	    public List<String> getMissingKeywords() {
	        return missingKeywords;
	    }

	    public void setMissingKeywords(List<String> missingKeywords) {
	        this.missingKeywords = missingKeywords;
	    }
}
