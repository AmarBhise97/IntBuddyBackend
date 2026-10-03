package com.IntBuddy.IntBuddy.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.IntBuddy.IntBuddy.DTO.ExperianceDTO;
import com.IntBuddy.IntBuddy.Entity.CommentEntity;
import com.IntBuddy.IntBuddy.Entity.ExperianceEntity;
import com.IntBuddy.IntBuddy.Entity.UserEntity;
import com.IntBuddy.IntBuddy.Exception.DataisEmptyException;
import com.IntBuddy.IntBuddy.Repository.ExperianceRepository;
import com.IntBuddy.IntBuddy.Repository.UserRepository;

@Service
public class ExperianceService {

	@Autowired
	private ExperianceRepository experianceRepo;

	@Autowired
	private UserRepository userRepository;

	
	// Add experience

	// ===============================
	// ADD EXPERIENCE
	// ===============================
	public ExperianceDTO addExperianceDTO(
	        ExperianceEntity exp,
	        Long userId
	) throws DataisEmptyException {

	    // Check user ID
	    if (userId == null) {

	        throw new DataisEmptyException(
	                "User ID is required"
	        );
	    }

	    // Find user
	    UserEntity user =
	            userRepository.findById(userId)
	            .orElseThrow(() ->
	                    new DataisEmptyException(
	                            "User not found with id: "
	                            + userId
	                    )
	            );

	    // Set user
	    exp.setUser(user);

	    // Save experience
	    ExperianceEntity saved =
	            experianceRepo.save(exp);

	    return convertToDTO(saved);
	}
	// AddExcetiance Convert to the DTO

	public ExperianceDTO convertToDTO(ExperianceEntity exp) {

		ExperianceDTO dto = new ExperianceDTO();

		dto.setExperiance_ID(exp.getExperiance_ID());
		dto.setCompanyName(exp.getCompanyName());
		dto.setPosition(exp.getPosition());
		dto.setDate(exp.getDate());
		dto.setDetails(exp.getDetails());
		dto.setResult(exp.isResult());
		dto.setRole(exp.getRole());
		dto.setExperianceinyear(exp.getExperianceinyear());
		dto.setResumeName(exp.getResumeName());
		dto.setResumeType(exp.getResumeType());
		

		if (exp.getUser() != null) {
			dto.setFullName(exp.getUser().getFullName());
			// optional but better:
			// dto.setUserId(exp.getUser().getId());
		}

		return dto;
	}

	// Get All Experience

	public Page<ExperianceDTO> getAllExperiance(Pageable pageable) {

	    return experianceRepo.findAll(pageable).map(experience -> {

	        ExperianceDTO dto = new ExperianceDTO();

	        dto.setExperiance_ID(
	                experience.getExperiance_ID()
	        );

	        dto.setCompanyName(
	                experience.getCompanyName()
	        );

	        dto.setPosition(
	                experience.getPosition()
	        );

	        dto.setDate(
	                experience.getDate()
	        );

	        dto.setDetails(
	                experience.getDetails()
	        );

	        dto.setResult(
	                experience.isResult()
	        );

	        dto.setRole(
	                experience.getRole()
	        );

	        dto.setExperianceinyear(
	                experience.getExperianceinyear()
	        );

	        dto.setResumeName(
	                experience.getResumeName()
	        );

	        dto.setResumeType(
	                experience.getResumeType()
	        );


	        // User null check
	        if (experience.getUser() != null) {

	            dto.setFullName(
	                    experience.getUser().getFullName()
	            );

	        }


	        return dto;
	    });
	}

	// search by company name

//	public List<ExperianceDTO> searchByPosition1(String position, Pageable pageable) throws DataisEmptyException {
//
//		Page<ExperianceEntity> page = experianceRepo.searchByPosition(position, pageable);
//
//		if (page.isEmpty()) {
//			throw new DataisEmptyException("No experience found for position: " + position);
//		}
//
//		return page.stream().map(exp -> {
//			ExperianceDTO dto = new ExperianceDTO();
//			dto.setExperiance_ID(exp.getExperiance_ID());
//			dto.setCompanyName(exp.getCompanyName());
//			dto.setDate(exp.getDate());
//			dto.setDetails(exp.getDetails());
//			dto.setPosition(exp.getPosition());
//			dto.setResult(exp.isResult());
//			//dto.setFullName(exp.getUser().getFullName());
//
//			if (exp.getUser() != null) {
//				dto.setFullName(exp.getUser().getFullName());
//			}
//
//			return dto;
//		}).collect(Collectors.toList());
//	}

	// Search By Position

	public List<ExperianceDTO> searchByPosition(String position, Pageable pageable) throws DataisEmptyException {

		Page<ExperianceEntity> page = experianceRepo.searchByPosition(position, pageable);

		if (page.isEmpty()) {
			throw new DataisEmptyException("No experience found for position: " + position);
		}

		return page.stream().map(exp -> {
			ExperianceDTO dto = new ExperianceDTO();
			dto.setExperiance_ID(exp.getExperiance_ID());
			dto.setCompanyName(exp.getCompanyName());
			dto.setDate(exp.getDate());
			dto.setDetails(exp.getDetails());
			dto.setPosition(exp.getPosition());
			dto.setResult(exp.isResult());
			

			dto.setFullName(exp.getUser().getFullName());
			if (exp.getUser() != null) {
				dto.setFullName(exp.getUser().getFullName());
			}

			return dto;
		}).collect(Collectors.toList());
	}

	
	
	public List<ExperianceDTO> searchAll(String keyword, Pageable pageable)
	        throws DataisEmptyException {

	    Page<ExperianceEntity> page =
	            experianceRepo.searchAll(keyword, pageable);

	    if (page.isEmpty()) {
	        throw new DataisEmptyException("No data found");
	    }

	    return page.stream().map(exp -> {

	        ExperianceDTO dto = new ExperianceDTO();

	        dto.setExperiance_ID(exp.getExperiance_ID());
	        dto.setCompanyName(exp.getCompanyName());
	        dto.setPosition(exp.getPosition());
	        dto.setRole(exp.getRole());
	        dto.setExperianceinyear(exp.getExperianceinyear());
	        dto.setDetails(exp.getDetails());
	        dto.setResult(exp.isResult());
	        dto.setResumeName(exp.getResumeName());
	        dto.setResumeType(exp.getResumeType());

	        if(exp.getUser()!=null){
	            dto.setFullName(exp.getUser().getFullName());
	        }

	        return dto;

	    }).collect(Collectors.toList());

	}
	// Get Experience ID

	public ExperianceDTO getEnperianceid(Long id) {

		Optional<ExperianceEntity> optionalExp = experianceRepo.findById(id);

		if (optionalExp.isEmpty()) {
			return null; //
		}

		ExperianceEntity idd = optionalExp.get();

		ExperianceDTO experiance2 = new ExperianceDTO();
		experiance2.setExperiance_ID(idd.getExperiance_ID());
		experiance2.setCompanyName(idd.getCompanyName());
		experiance2.setDate(idd.getDate());
		experiance2.setDetails(idd.getDetails());
		experiance2.setPosition(idd.getPosition());
		experiance2.setResult(idd.isResult());
		experiance2.setFullName(idd.getUser().getFullName());
		experiance2.setRole(idd.getRole());
		experiance2.setExperianceinyear(idd.getExperianceinyear());
		experiance2.setResumeName(idd.getResumeName());
		experiance2.setResumeType(idd.getResumeType());

		return experiance2;
	}

	// Update Experience

	public ExperianceDTO updateExperiance(Long experiance_ID, ExperianceDTO dto) throws Exception {

		Optional<ExperianceEntity> optionalExp = experianceRepo.findById(experiance_ID);

		if (optionalExp.isEmpty()) {
			return null;
		}

		ExperianceEntity existing = optionalExp.get();

		existing.setCompanyName(dto.getCompanyName());
		existing.setDate(dto.getDate());
		existing.setDetails(dto.getDetails());
		existing.setPosition(dto.getPosition());
		existing.setResult(dto.isResult());

		ExperianceEntity saved = experianceRepo.save(existing);

		ExperianceDTO response = new ExperianceDTO();
		response.setExperiance_ID(saved.getExperiance_ID());
		response.setCompanyName(saved.getCompanyName());
		response.setDate(saved.getDate());
		response.setDetails(saved.getDetails());
		response.setPosition(saved.getPosition());
		response.setResult(saved.isResult());
		response.setFullName(saved.getUser().getFullName());

		return response;
	}

	// delete Experience

	public boolean deleteExperiance(Long experiance_ID) {
		Optional<ExperianceEntity> optionalComment = experianceRepo.findById(experiance_ID);

		if (optionalComment.isEmpty()) {
			return false;
		}

		experianceRepo.delete(optionalComment.get());
		return true;
	}

}
