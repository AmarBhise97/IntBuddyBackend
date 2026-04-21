package com.IntBuddy.IntBuddy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void RegistrationEmail(String toEmail, String name) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("malusarenamrata88@gmail.com");
		message.setTo(toEmail);
		message.setSubject("Welcome to IntBuddy ...!/n Your Interview Preparation our Hand...!");

		message.setText("Hello " + name + ",\n\n" + "Your registration is successful!\n\n" + "Welcome to IntBuddy.\n\n"
				+ "Happy Reviewing ...!");

		mailSender.send(message);
	}
}
