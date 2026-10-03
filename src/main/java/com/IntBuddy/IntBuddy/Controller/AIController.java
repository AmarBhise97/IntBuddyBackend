package com.IntBuddy.IntBuddy.Controller;

import org.springframework.web.bind.annotation.*;

import com.IntBuddy.IntBuddy.DTO.AIRequest;
import com.IntBuddy.IntBuddy.DTO.AIResponse;
import com.IntBuddy.IntBuddy.Service.AIService;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "https://your-frontend-domain.vercel.app"
	})
public class AIController {
	
	 private final AIService aiService;

	    public AIController(AIService aiService) {
	        this.aiService = aiService;
	    }

	    @PostMapping("/chat")
	    public AIResponse chat(@RequestBody AIRequest request) {

	        String reply = aiService.askAI(request.getMessage());

	        return new AIResponse(reply);
	    }
	    
	    @GetMapping("/models")
	    public String models() {
	        return aiService.getModels();
	    }
	

}
