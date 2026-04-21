package com.IntBuddy.IntBuddy.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.IntBuddy.IntBuddy.DTO.CommentDTO2;
import com.IntBuddy.IntBuddy.DTO.ExperianceDTO2;
import com.IntBuddy.IntBuddy.DTO.UserDTO;
import com.IntBuddy.IntBuddy.Entity.UserEntity;
import com.IntBuddy.IntBuddy.Repository.CommentRepository;
import com.IntBuddy.IntBuddy.Repository.ExperianceRepository;
import com.IntBuddy.IntBuddy.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private ExperianceRepository experiancerepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepo;

	// Create user
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}

	// Get user by id
	public UserDTO getUserById(Long id) throws Exception {
		return userRepository.findById(id).map((user) -> {
			UserDTO us = new UserDTO();
			us.setId(user.getId());
			us.setName(user.getName());
			us.setEmail(user.getEmail());
			us.setPhoneno(user.getPhoneno());
			us.setGender(user.getGender());
			us.setCountry(user.getCountry());
			

			List<ExperianceDTO2> experiance = user.getExperiance().stream().map((exp) -> {
				ExperianceDTO2 experiance2 = new ExperianceDTO2();
				experiance2.setCompanyName(exp.getCompanyName());
				experiance2.setDate(exp.getDate());
				experiance2.setDetails(exp.getDetails());
				experiance2.setPosition(exp.getPosition());
				experiance2.setResult(exp.isResult());

				return experiance2;
			}).collect(Collectors.toList());

			List<CommentDTO2> comment = user.getComment().stream().map((com) -> {

				CommentDTO2 comment2 = new CommentDTO2();
				comment2.setContent(com.getContent());

				return comment2;

			}).collect(Collectors.toList());

			us.setComment(comment);
			us.setExperiance(experiance);

			return us;
		}).orElseThrow(() -> new Exception("not the valid user" + id));
	}

	public Page<UserDTO> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(user -> {

			UserDTO us = new UserDTO();
			us.setId(user.getId());
			us.setName(user.getName());
			us.setEmail(user.getEmail());
			us.setPhoneno(user.getPhoneno());
			us.setGender(user.getGender());
			us.setCountry(user.getCountry());
			us.setState(user.getState());

			List<ExperianceDTO2> experiance = user.getExperiance().stream().map(exp -> {
				ExperianceDTO2 e = new ExperianceDTO2();
				e.setCompanyName(exp.getCompanyName());
				e.setPosition(exp.getPosition());
				e.setDetails(exp.getDetails());
				e.setResult(exp.isResult());
				e.setDate(exp.getDate());
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
	public UserEntity updateUserEntity(Long id, UserEntity user) {
		user.setId(id);
		return userRepository.save(user);

	}
}