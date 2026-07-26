package com.IntBuddy.IntBuddy.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IntBuddy.IntBuddy.DTO.QuestionDTO;
import com.IntBuddy.IntBuddy.DTO.QuestionRequest;

import com.IntBuddy.IntBuddy.Entity.AnonymousExperienceEntity;
import com.IntBuddy.IntBuddy.Entity.InterviewQuestionEntity;
import com.IntBuddy.IntBuddy.Repository.AnonymousRepository;
import com.IntBuddy.IntBuddy.Repository.QuestionRepository;

@Service
public class AnonymousService {
	
	
	 @Autowired
	    private AnonymousRepository anonymousRepository;

	    @Autowired
	    private QuestionRepository questionRepository;

	    public AnonymousExperienceEntity saveExperience(
	            AnonymousExperienceEntity experience){

	        return anonymousRepository.save(experience);

	    }

	    public void saveQuestions(QuestionRequest request){

	        AnonymousExperienceEntity experience =
	                anonymousRepository.findById(request.getExperienceId())
	                .orElseThrow();

	        List<InterviewQuestionEntity> list =
	                new ArrayList<>();

	        for(QuestionDTO dto : request.getQuestions()){

	            InterviewQuestionEntity q =
	                    new InterviewQuestionEntity();

	            q.setQuestion(dto.getQuestion());

	            q.setAnswer(dto.getAnswer());

	            q.setExperience(experience);

	            list.add(q);

	        }

	        questionRepository.saveAll(list);

	    }

}
