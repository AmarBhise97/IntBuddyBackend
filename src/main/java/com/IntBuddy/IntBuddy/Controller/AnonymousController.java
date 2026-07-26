package com.IntBuddy.IntBuddy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IntBuddy.IntBuddy.DTO.QuestionRequest;
import com.IntBuddy.IntBuddy.Entity.AnonymousExperienceEntity;
import com.IntBuddy.IntBuddy.Service.AnonymousService;

@RestController
@RequestMapping("/anonymous")
@CrossOrigin(origins = "http://localhost:5173")
public class AnonymousController {
	 @Autowired
	    private AnonymousService anonymousService;

	    @PostMapping("/save")
	    public AnonymousExperienceEntity save(

	            @RequestBody AnonymousExperienceEntity entity){

	        return anonymousService.saveExperience(entity);

	    }

	    @PostMapping("/question/save")
	    public ResponseEntity<String> saveQuestions(

	            @RequestBody QuestionRequest request){

	        anonymousService.saveQuestions(request);

	        return ResponseEntity.ok("Saved");

	    }

}
