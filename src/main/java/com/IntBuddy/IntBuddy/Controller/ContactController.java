package com.IntBuddy.IntBuddy.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.IntBuddy.IntBuddy.DTO.ContactDTO;
import com.IntBuddy.IntBuddy.Service.EmailService;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {
	
	 @Autowired
	    private EmailService emailService;

	    @PostMapping("/send")
	    public String sendMessage(@RequestBody ContactDTO dto) {

	        emailService.contactMail(
	                dto.getName(),
	                dto.getEmail(),
	                dto.getMessage()
	        );

	        return "Message Sent Successfully";
	    }
	

}
