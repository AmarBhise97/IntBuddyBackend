package com.IntBuddy.IntBuddy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    public void experiencemail(String toEmail, String name) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bhiseamarwagholi@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Experience Added Successfully..!");

            message.setText(
                    "Hello " + name + ",\n\n" +
                    "Your experience details have been added successfully.\n\n" +
                    "Thank you for updating your profile.\n\n" +
                    "Best Regards,\n" +
                    "IntBuddy Team"
            );

            mailSender.send(message);

        }catch (Exception e) {
            throw new RuntimeException("Mail failed: " + e.getMessage(), e);
        }
    }
    
    public void Otpemail(String toEmail, String otp) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bhiseamarwagholi@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Regarding to Your OTP Code ");
            message.setText("Opt is "+otp+"  OTP is valid for only 5 Minute...!");
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Mail failed: " + e.getMessage(), e);
        }
    }    
    
    //After Registration
    public void RegistrationEmail(String toEmail, String name) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("bhiseamarwagholi@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Welcome to IntBuddy...!");

            String htmlContent =
                    "<html>" +
                    "<body style='font-family: Arial; background-color:#f4f4f4; padding:20px;'>" +
                    "<div style='max-width:600px; margin:auto; background:#fff; padding:20px; border-radius:10px;'>" +

                    "<h2 style='color:#4CAF50; text-align:center;'>Welcome to IntBuddy...!</h2>" +

                    "<p>Hello <b>" + name + "</b>,</p>" +

                    "<p>Your registration is <b>Successful</b>!</p>" +

                    "<p>We are excited to have you on board.</p>" +

                    "<div style='text-align:center; margin:20px;'>" +
                    "<a href='#' style='background:#4CAF50; color:white; padding:10px 20px; text-decoration:none; border-radius:5px;'>Login</a>" +
                    "</div>" +

                    "<p>Happy Interview Preparation! </p>" +

                    "<p>Regards,<br><b>Team IntBuddy</b></p>" +

                    "<hr>" +
                    "<p style='font-size:12px; text-align:center; color:gray;'>© malusarenamrata88@gmail.com</p>" +

                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true); 

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Mail failed: " + e.getMessage(), e);
        }
        }
    }

