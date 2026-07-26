package com.IntBuddy.IntBuddy.Service;

import org.springframework.web.multipart.MultipartFile;

import com.IntBuddy.IntBuddy.DTO.JDMatchResponse;
import com.IntBuddy.IntBuddy.DTO.ResumeAnalysisResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.IntBuddy.IntBuddy.DTO.ResumeAnalysisResponse;
import com.IntBuddy.IntBuddy.Service.ResumeService;

@Service
public class ResumeServiceImpl  implements ResumeService {

	  @Override
	    public ResumeAnalysisResponse analyzeResume(MultipartFile resume) {

	        ResumeAnalysisResponse response = new ResumeAnalysisResponse();

	        try {

	            String text = extractText(resume).toLowerCase();

	            int score = 40;

	            List<String> suggestions = new ArrayList<>();

	            if(text.contains("java"))
	                score += 10;
	            else
	                suggestions.add("Add Java skill");

	            if(text.contains("spring"))
	                score += 10;
	            else
	                suggestions.add("Add Spring Boot");

	            if(text.contains("mysql"))
	                score += 10;
	            else
	                suggestions.add("Add MySQL");

	            if(text.contains("project"))
	                score += 10;
	            else
	                suggestions.add("Mention Projects");

	            if(text.contains("github"))
	                score += 10;
	            else
	                suggestions.add("Add GitHub Profile");

	            if(score > 100)
	                score = 100;

	            response.setScore(score);
	            response.setSuggestions(suggestions);

	        }
	        catch(Exception e) {

	            response.setScore(0);
	            response.setSuggestions(
	                    Arrays.asList("Unable to analyze resume.")
	            );
	        }

	        return response;
	    }

	    @Override
	    public JDMatchResponse matchResumeWithJD(MultipartFile resume, String jd) {

	        JDMatchResponse response = new JDMatchResponse();

	        try {

	            String resumeText = extractText(resume).toLowerCase();

	            String jobDescription = jd.toLowerCase();

	            List<String> strengths = new ArrayList<>();
	            List<String> missing = new ArrayList<>();

	            int total = 0;
	            int matched = 0;

	            String[] keywords = jobDescription.split("\\s+");

	            for(String word : keywords){

	                word = word.trim();

	                if(word.length() < 4)
	                    continue;

	                total++;

	                if(resumeText.contains(word)){

	                    matched++;
	                    strengths.add(word);

	                }else{

	                    missing.add(word);

	                }

	            }

	            int match = 0;

	            if(total > 0){

	                match = (matched * 100) / total;

	            }

	            response.setMatch(match);
	            response.setStrengths(strengths);
	            response.setMissingKeywords(missing);

	        }
	        catch(Exception e){

	            response.setMatch(0);
	            response.setStrengths(new ArrayList<>());
	            response.setMissingKeywords(
	                    Arrays.asList("Unable to analyze resume.")
	            );

	        }

	        return response;

	    }

	    private String extractText(MultipartFile file) throws IOException {

	        PDDocument document = PDDocument.load(file.getInputStream());

	        PDFTextStripper stripper = new PDFTextStripper();

	        String text = stripper.getText(document);

	        document.close();

	        return text;

	    }


}
