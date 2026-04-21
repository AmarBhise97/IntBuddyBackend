package com.IntBuddy.IntBuddy.Controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import com.IntBuddy.IntBuddy.DTO.CommentDTO;
import com.IntBuddy.IntBuddy.Entity.CommentEntity;
import com.IntBuddy.IntBuddy.Service.CommentService;

@RestController
@RequestMapping("/Comment")
public class CommentController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CommentService service;

	public CommentController(CommentService service) {
		this.service = service;
	}

	// ADD comment
	@PostMapping("/addComent")
	@CacheEvict(value = "comment", allEntries = true)
	public CommentEntity addComment(@RequestBody CommentEntity comment) {
		CommentEntity saved = service.addcomment(comment);
		return saved;
	}

	@GetMapping("/comments/search")
	@Cacheable(value = "comment", key = "#content + '-' + #page + '-' + #size + '-' + #sortBy + '-' + #direction")
	public List<CommentDTO> searchComments(@RequestParam String content, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "content") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) throws InterruptedException {

		// clean input
		Thread.sleep(5000);
		content = content.trim();

		// validate sort field
		List<String> allowed = List.of("comment_id", "content");
		if (!allowed.contains(sortBy)) {
			sortBy = "content";
		}

		// sorting
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CommentDTO> pageData = service.searchComments(content, pageable);

		return pageData.getContent(); // returns empty list if no data
	}

	// Get all comment
	@GetMapping("/getallcomment")
	@Cacheable(value = "comment", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
	public List<CommentDTO> getAllComments(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "content") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction) throws InterruptedException {

	    Thread.sleep(5000);

	    // validate sort field
	    List<String> allowed = List.of("comment_id", "content");
	    if (!allowed.contains(sortBy)) {
	        sortBy = "comment_id";
	    }

	    Sort sort = direction.equalsIgnoreCase("desc")
	            ? Sort.by(sortBy).descending()
	            : Sort.by(sortBy).ascending();

	    Pageable pageable = PageRequest.of(page, size, sort);

	    Page<CommentDTO> pageData = service.getAllComment(pageable);

	    return pageData.getContent(); // empty list if no data
	}

	@GetMapping("/getCommentId/{id}")
	@Cacheable(value = "comment", key = "#comment_id")
	public CommentDTO getCommentId(@PathVariable("id") Long comment_id) throws Exception {
		Thread.sleep(5000);
		return service.getCommentbyid(comment_id);
	}

	// UPDATE comment
	@PutMapping("/updateComment/{id}")
	@CachePut(value = "comment", key = "#comment_id")
	public CommentDTO updateComment(@PathVariable("id") Long comment_id, @RequestBody CommentDTO comment)
			throws Exception {
        Thread.sleep(5000);
		return service.updateComment(comment_id, comment);
	}

	// DELETE comment
	@DeleteMapping("/deletComment/{id}")
	@CacheEvict(value = "comment", key = "#commnet_id")
	public String deleteComment(@PathVariable("id") Long commnet_id) {
		return " delete succesfully  " + service.deleteComment(commnet_id);
	}
}