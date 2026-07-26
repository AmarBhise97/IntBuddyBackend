package com.IntBuddy.IntBuddy.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.IntBuddy.IntBuddy.DTO.CommentDTO2;
import com.IntBuddy.IntBuddy.DTO.ExperianceDTO2;
import com.IntBuddy.IntBuddy.DTO.UserDTO;
import com.IntBuddy.IntBuddy.Entity.UserEntity;
import com.IntBuddy.IntBuddy.Enum.Role;
import com.IntBuddy.IntBuddy.Exception.DataisEmptyException;
import com.IntBuddy.IntBuddy.Repository.CommentRepository;
import com.IntBuddy.IntBuddy.Repository.ExperianceRepository;
import com.IntBuddy.IntBuddy.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ExperianceRepository experiancerepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepo;

	Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private OtpService service;

	boolean flag = false;

	String otp2;
	
	

	// Create user
	public UserEntity createUser(UserEntity user) throws Exception {

		if (!flag) {
			throw new Exception("Otp is not verified......");
		}
		log.info("sending the otp to " + user.getPhoneno());

		if (userRepository.existsByEmail(user.getEmail())) {
		    throw new DataisEmptyException("Email already registered: " + user.getEmail());
		}

		// Encrypt Password Before Saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setRole(Role.USER);
		return userRepository.save(user);

	}
	
	
	
	
	//Get User ID

	public UserDTO getUserById(Long id) {

	    Optional<UserEntity> optionalUser = userRepository.findById(id);

	    if (optionalUser.isEmpty()) {
	        return null;
	    }

	    UserEntity user = optionalUser.get();


	    System.out.println("User Id : " + user.getId());
	    System.out.println("Experience Count : " + user.getExperiance().size());

	    UserDTO us = new UserDTO();
	    us.setId(user.getId());
	    us.setFullName(user.getFullName());
	    us.setEmail(user.getEmail());
	    us.setPhoneno(user.getPhoneno());
	    us.setGender(user.getGender());
	    us.setCountry(user.getCountry());
	    us.setState(user.getState());

	    List<ExperianceDTO2> experiance = user.getExperiance().stream().map(exp -> {
	        ExperianceDTO2 experiance2 = new ExperianceDTO2();
	        experiance2.setCompanyName(exp.getCompanyName());
	        experiance2.setDate(exp.getDate());
	        experiance2.setDetails(exp.getDetails());
	        experiance2.setPosition(exp.getPosition());
	        experiance2.setResult(exp.isResult());
	        experiance2.setExperianceinyear(exp.getExperianceinyear());
	        experiance2.setRole(exp.getRole());
	        experiance2.setExperiance_ID(exp.getExperiance_ID());
	        experiance2.setResumeName(exp.getResumeName());
	        experiance2.setResumeType(exp.getResumeType());
	        return experiance2;
	    }).collect(Collectors.toList());

	    List<CommentDTO2> comment = user.getComment().stream().map(com -> {
	        CommentDTO2 comment2 = new CommentDTO2();
	        comment2.setContent(com.getContent());
	        return comment2;
	    }).collect(Collectors.toList());
	    System.out.println("DTO Experience Size: " + experiance.size());

	    for (ExperianceDTO2 dto : experiance) {
	        System.out.println("DTO Company: " + dto.getCompanyName());
	    }

	    us.setComment(comment);
	    us.setExperiance(experiance);

	    return us;
	}

	
	//Get All Users
	
	public Page<UserDTO> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(user -> {

			UserDTO us = new UserDTO();
			us.setId(user.getId());
			us.setFullName(user.getFullName());
			us.setEmail(user.getEmail());
			us.setPhoneno(user.getPhoneno());
			us.setGender(user.getGender());
			us.setCountry(user.getCountry());
			us.setState(user.getState());
			us.setState(user.getState());

			List<ExperianceDTO2> experiance = user.getExperiance().stream().map(exp -> {
				ExperianceDTO2 e = new ExperianceDTO2();
				e.setCompanyName(exp.getCompanyName());
				e.setPosition(exp.getPosition());
				e.setDetails(exp.getDetails());
				e.setResult(exp.isResult());
				e.setDate(exp.getDate());
				e.setRole(exp.getRole());
				e.setExperianceinyear(exp.getExperianceinyear());
				e.setExperiance_ID(exp.getExperiance_ID());
		        e.setResumeName(exp.getResumeName());
		        e.setResumeType(exp.getResumeType());
				return e;
				
			}).collect(Collectors.toList());

			List<CommentDTO2> comment = user.getComment().stream().map(com -> {
				CommentDTO2 c = new CommentDTO2();
				c.setContent(com.getContent());
				return c;
			}).collect(Collectors.toList());

			us.setExperiance(experiance);
			us.setComment(comment);

			return us;
		});
	}

//    // Find by email
//    public UserEntity findByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }
//    
	
	
	//Update Users
	
	public UserEntity updateUserEntity(Long id, UserEntity user) {

		Optional<UserEntity> optionalUser = userRepository.findById(id);

		if (optionalUser.isEmpty()) {
			return null; // ✅ let controller handle exception
		}

		UserEntity existingUser = optionalUser.get();

		// ✅ update fields manually
		existingUser.setFullName(user.getFullName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPhoneno(user.getPhoneno());
		existingUser.setGender(user.getGender());
		existingUser.setCountry(user.getCountry());
		existingUser.setState(user.getState());
		if(user.getPassword()!=null &&
				!user.getPassword().isBlank()){

				existingUser.setPassword(
				passwordEncoder.encode(user.getPassword())
				);

				}
		existingUser.setOtp(user.getOtp());

		return userRepository.save(existingUser);
	}
	
	
	//OTP Verify

//	public String verifyOtp(@PathVariable(value = "phone") String phone) {
//
//		otp2 = service.sendOtp(phone);
//
//		return otp2;
//	}
//
//  OTP2 Verify
//	
	public boolean verifyOtp2(@PathVariable(value = "otp") String otp) {

		flag = otp2.equals(otp);

		return flag;
	}
	
	@Autowired
	private EmailService emailService;

	public String verifyOtp(String email) {

	    otp2 = String.valueOf((int)((Math.random() * 900000) + 100000));

	    emailService.Otpemail(email, otp2);

	    return "OTP Sent Successfully";
	}
	
	public UserEntity loginUser(String email, String password) throws Exception {

	    Optional<UserEntity> user = userRepository.findByEmail(email);

	    if (user.isEmpty()) {
	        throw new Exception("Invalid Email");
	    }

	    if (!passwordEncoder.matches(password, user.get().getPassword())) {
	        throw new Exception("Invalid Password");
	    }

	    return user.get();
	}
	// Check Email
	public boolean checkEmail(String email) {
	    return userRepository.existsByEmail(email);
	}

	// Check Phone Number
	public boolean checkPhone(String phoneno) {
	    return userRepository.existsByPhoneno(phoneno);
	}

}