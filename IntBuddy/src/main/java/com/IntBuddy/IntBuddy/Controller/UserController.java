package com.IntBuddy.IntBuddy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;

import com.IntBuddy.IntBuddy.DTO.UserDTO;
import com.IntBuddy.IntBuddy.Entity.UserEntity;
import com.IntBuddy.IntBuddy.Service.EmailService;
import com.IntBuddy.IntBuddy.Service.UserService;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	    @Autowired
	    private UserService service;

	    @Autowired
	    private EmailService emailService;

	    // CREATE
	    @PostMapping("/register")
	    @CacheEvict(value = "user", allEntries = true)
	
	    public UserEntity createUser(@RequestBody UserEntity user) throws Exception {

	        System.out.println("STEP 1: Saving user");

	        UserEntity newUser = service.createUser(user);

	        System.out.println("STEP 2: Calling email");

	        emailService.RegistrationEmail(newUser.getEmail(), newUser.getName(),newUser.getOtp());

	        System.out.println("STEP 3: Email sent");

	        return newUser;
	    }
//	    public UserEntity createUser(@RequestBody UserEntity user) {
//	        UserEntity newUser = service.createUser(user);
//	        emailService.RegistrationEmail(newUser.getEmail(), newUser.getName());
//	        return newUser;
//	    }

	    // GET ALL
	    @GetMapping("/get")
	    @Cacheable(value = "user", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
	    public List<UserDTO> getAllUsers(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(defaultValue = "id") String sortBy,
	            @RequestParam(defaultValue = "asc") String direction) throws InterruptedException {

	        Thread.sleep(5000);

	        Sort sort = direction.equalsIgnoreCase("desc")
	                ? Sort.by(sortBy).descending()
	                : Sort.by(sortBy).ascending();

	        Pageable pageable = PageRequest.of(page, size, sort);

	        Page<UserDTO> users = service.getAllUsers(pageable);

	        return users.getContent();
	    }

	    // GET BY ID
	    @GetMapping("/{id}")
	    @Cacheable(value = "user", key = "#id")
	    public UserDTO getUser(@PathVariable Long id) throws Exception {
	        return service.getUserById(id);
	    }

	    // UPDATE
	    @PutMapping("/updateUser/{id}")
	    @CachePut(value = "user", key = "#id")
	    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
	        return service.updateUserEntity(id, user);
	    }
	    
//	    @PostMapping("/enterphone/{phone}")
//	    public String verfyotp(@RequestParam(value="phone") String phone)
//	    {
//	    	return service.verifyOtp(phone);
//	    	
//	    }
//	    
//	    
//        @PostMapping("/verifyotp/{otp}")
//        public boolean veri(@RequestParam(value="otp") String otp)
//        {
//        	
//        	return service.verifyOtp2(otp);
//        }
//
	}