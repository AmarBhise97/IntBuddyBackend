package com.IntBuddy.IntBuddy.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.IntBuddy.IntBuddy.DTO.ExperianceDTO;
import com.IntBuddy.IntBuddy.Entity.ExperianceEntity;

import com.IntBuddy.IntBuddy.Repository.ExperianceRepository;

@Service
public class ExperianceService {

	@Autowired
	private ExperianceRepository experianceRepo;

	// Add experience
	public ExperianceEntity addExperiance(ExperianceEntity exp) {
		return experianceRepo.save(exp);
	}

	// Get all
	public Page<ExperianceDTO> getAllExperiance(Pageable pageable) {

		return experianceRepo.findAll(pageable).map(experiance -> {
			ExperianceDTO dto = new ExperianceDTO();
			dto.setExperiance_ID(experiance.getExperiance_ID());
			dto.setCompanyName(experiance.getCompanyName());
			dto.setDate(experiance.getDate());
			dto.setDetails(experiance.getDetails());
			dto.setPosition(experiance.getPosition());
			dto.setResult(experiance.isResult());

			if (experiance.getUser() != null) {
				dto.setName(experiance.getUser().getName());
			}

			return dto;
		});
	}

	// search by company name
	public List<ExperianceDTO> searchByCompany(String companyName) {

		return experianceRepo.findByCompanyNameContainingIgnoreCase(companyName).stream().map(exp -> {
			ExperianceDTO dto = new ExperianceDTO();
			dto.setExperiance_ID(exp.getExperiance_ID());
			dto.setCompanyName(exp.getCompanyName());
			dto.setDate(exp.getDate());
			dto.setDetails(exp.getDetails());
			dto.setPosition(exp.getPosition());
			dto.setResult(exp.isResult());

			if (exp.getUser() != null) {
				dto.setName(exp.getUser().getName());
			}

			return dto;
		}).collect(Collectors.toList());
	}

	// search by position
	public List<ExperianceDTO> searchByPosition(String position, Pageable pageable) {

		return experianceRepo.findByPositionContainingIgnoreCase(position, pageable).stream().map(exp -> {
			ExperianceDTO dto = new ExperianceDTO();
			dto.setExperiance_ID(exp.getExperiance_ID());
			dto.setCompanyName(exp.getCompanyName());
			dto.setDate(exp.getDate());
			dto.setDetails(exp.getDetails());
			dto.setPosition(exp.getPosition());
			dto.setResult(exp.isResult());

			if (exp.getUser() != null) {
				dto.setName(exp.getUser().getName());
			}

			return dto;
		}).collect(Collectors.toList());
	}

	// Get id
	public ExperianceDTO getEnperianceid(Long id) throws Exception {
		return experianceRepo.findById(id).map((idd) -> {

			ExperianceDTO experiance2 = new ExperianceDTO();
			experiance2.setExperiance_ID(idd.getExperiance_ID());
			experiance2.setCompanyName(idd.getCompanyName());
			experiance2.setDate(idd.getDate());
			experiance2.setDetails(idd.getDetails());
			experiance2.setPosition(idd.getPosition());
			experiance2.setResult(idd.isResult());
			experiance2.setName(idd.getUser().getName());

			return experiance2;

		}).orElseThrow(() -> new Exception("not the valid experiance id " + id));

	}

	public ExperianceDTO updateExperiance(Long experiance_ID, ExperianceDTO dto) throws Exception {

		ExperianceEntity existing = experianceRepo.findById(experiance_ID)
				.orElseThrow(() -> new Exception("Invalid experiance id " + experiance_ID));

		// update fields from DTO
		existing.setCompanyName(dto.getCompanyName());
		existing.setDate(dto.getDate());
		existing.setDetails(dto.getDetails());
		existing.setPosition(dto.getPosition());
		existing.setResult(dto.isResult());

		// save updated entity
		ExperianceEntity saved = experianceRepo.save(existing);

		// convert back to DTO
		ExperianceDTO response = new ExperianceDTO();
		response.setExperiance_ID(saved.getExperiance_ID());
		response.setCompanyName(saved.getCompanyName());
		response.setDate(saved.getDate());
		response.setDetails(saved.getDetails());
		response.setPosition(saved.getPosition());
		response.setResult(saved.isResult());
		response.setName(saved.getUser().getName());

		return response;
	}

	// delete Experiance
	public String deleteExperiance(Long experiance_ID) {
		experianceRepo.deleteById(experiance_ID);
		return "Delete Succesfully" + experiance_ID;

	}

}
