package com.IntBuddy.IntBuddy.Service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OtpService {
	
	// Phone Number OTP sender 

	private static final String ACCOUNT_SID = "{}";
	private static final String AUTH_TOKEN = "{}";
	private static final String FROM_NUMBER = "{}"; // Twilio number
	
	

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
	String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

	public String sendOtp(String toPhoneNumber) {
		System.out.println("Number:"   );
		Message message = Message.creator(new PhoneNumber("+91" + toPhoneNumber), new PhoneNumber(FROM_NUMBER),
				"Your OTP is: " + otp + " (Valid for 5 mins)").create();
		return otp;
	}
	
 
	//Verify OTP  
	public boolean verifyotp(String otp2) {
		return otp.equals(otp2);
	}
}