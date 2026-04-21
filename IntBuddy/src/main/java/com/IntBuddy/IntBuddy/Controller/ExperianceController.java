package com.IntBuddy.IntBuddy.Controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IntBuddy.IntBuddy.DTO.ExperianceDTO;
import com.IntBuddy.IntBuddy.Entity.ExperianceEntity;
import com.IntBuddy.IntBuddy.Service.ExperianceService;

@RestController
@RequestMapping("/Experiance")
public class ExperianceController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ExperianceService service;

	// ADD experience
	@PostMapping("/add")
	@CacheEvict(value = "experiance", allEntries = true)
	public ExperianceEntity addExperiance(@RequestBody ExperianceEntity exp) {
		ExperianceEntity saved = service.addExperiance(exp);
		return saved;
	}

	@GetMapping("/getexperiance")
	@Cacheable(value = "experiance", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
	public Map<String, Object> getAllExperiance(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "companyName") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) throws InterruptedException {

		// validate sort field
	
		List<String> allowed = List.of("experiance_ID", "companyName", "position", "date");
		if (!allowed.contains(sortBy)) {
			sortBy = "experiance_ID";
		}

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<ExperianceDTO> pageData = service.getAllExperiance(pageable);

		// prepare response
		Map<String, Object> response = new HashMap<>();
		response.put("data", pageData.getContent());
		response.put("currentPage", pageData.getNumber());
		response.put("totalPages", pageData.getTotalPages());
		response.put("totalItems", pageData.getTotalElements());

		return response;
	}
//	// SEARCH by company
//	@GetMapping("/search/company")
//
//	public ResponseEntity<List<ExperianceDTO>> searchCompany(@RequestParam String company) {
//		List<ExperianceDTO> list = service.searchByCompany(company);
//		return ResponseEntity.ok(list);
//	}

	@GetMapping("/position/search")
	@Cacheable(value = "experience", key = "#position + '-' + #page + '-' + #size + '-' + #sortBy + '-' + #direction")
	public List<ExperianceDTO> searchposition(@RequestParam String position, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "position") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) throws InterruptedException {

		// validate sort field

		List<String> allowed = List.of("position", "companyName", "date");
		if (!allowed.contains(sortBy)) {
			sortBy = "position";
		}

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		List<ExperianceDTO> pageData = service.searchByPosition(position, pageable);

		// if no data → return empty list instead of error
		return pageData;
	}

	@GetMapping("/getexperianceid/{id}")
	@Cacheable(value = "experiance", key = "#experiance_ID")
	public ExperianceDTO getExperianceId(@PathVariable("id") Long experiance_ID) throws Exception {

		return service.getEnperianceid(experiance_ID);
	}

	@PutMapping("/updateecperiance/{id}")
	@CachePut(value = "experiance", key = "#experiance_ID")
	public ExperianceDTO updateExperiance(@PathVariable("id") Long experiance_ID, @RequestBody ExperianceDTO experiance)
			throws Exception {

		return service.updateExperiance(experiance_ID, experiance);
	}

	@DeleteMapping("/deleteExperiance/{id}")
	@CacheEvict(value = "experiance", key = "#experiance_ID")
	public String deleteExperiance(@PathVariable("id") Long experiance_ID) throws InterruptedException {

		return "delete succesfully  " + service.deleteExperiance(experiance_ID);
	}
}
