package com.IntBuddy.IntBuddy.Service;

import org.springframework.web.multipart.MultipartFile;

import com.IntBuddy.IntBuddy.DTO.JDMatchResponse;
import com.IntBuddy.IntBuddy.DTO.ResumeAnalysisResponse;

public interface ResumeService {
	
	  ResumeAnalysisResponse analyzeResume(MultipartFile resume);

	    JDMatchResponse matchResumeWithJD(MultipartFile resume, String jd);

}
