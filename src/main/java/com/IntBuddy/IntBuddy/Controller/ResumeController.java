package com.IntBuddy.IntBuddy.Controller;

import java.util.List;

import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.IntBuddy.IntBuddy.DTO.JDMatchResponse;
import com.IntBuddy.IntBuddy.DTO.ResumeAnalysisResponse;
import com.IntBuddy.IntBuddy.Service.ResumeService;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "http://localhost:5173")
public class ResumeController {
	
     
	  @Autowired
	    private ResumeService resumeService;

	  @PostMapping(
		        value="/analyze",
		        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
		public ResponseEntity<ResumeAnalysisResponse> analyzeResume(
		        @RequestParam("resume") MultipartFile resume){

		    System.out.println("File Name = " + resume.getOriginalFilename());

		    return ResponseEntity.ok(
		            resumeService.analyzeResume(resume)
		    );
		}

	    @PostMapping("/match-jd")
	    public ResponseEntity<JDMatchResponse> matchResume(
	            @RequestParam("resume") MultipartFile resume,
	            @RequestParam("jd") String jd) {

	        return ResponseEntity.ok(
	                resumeService.matchResumeWithJD(resume, jd)
	        );
	    }
	    @GetMapping("/test")
	    public String test() {
	        return "Resume API Working";
	    }

}
