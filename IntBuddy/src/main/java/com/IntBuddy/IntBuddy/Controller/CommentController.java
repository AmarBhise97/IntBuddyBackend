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
import com.IntBuddy.IntBuddy.Exception.DataisEmptyException;
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

	// ADD Comment
	@PostMapping("/addComent")
	@CacheEvict(value = "comment", allEntries = true)
	public CommentEntity addComment(@RequestBody CommentEntity comment) {
		CommentEntity saved = service.addcomment(comment);
		return saved;
	}

	// Search Comment
	@GetMapping("/comments/search")
	@Cacheable(value = "comment", key = "#content + '-' + #page + '-' + #size + '-' + #sortBy + '-' + #direction")
	public List<CommentDTO> searchComments(@RequestParam String content, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "content") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) throws InterruptedException, DataisEmptyException {

		content = content.trim();

		List<String> allowed = List.of("comment_id", "content");
		if (!allowed.contains(sortBy)) {
			sortBy = "content";
		}

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CommentDTO> pageData = service.searchComments(content, pageable);

		if (pageData.isEmpty()) {
			throw new DataisEmptyException("No comments found for: " + content);
		}

		return pageData.getContent();
	}

	// Get All Comment
	@GetMapping("/getallcomment")
	@Cacheable(value = "comment", key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction")
	public List<CommentDTO> getAllComments(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "content") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) throws InterruptedException, DataisEmptyException {

		Thread.sleep(5000);

		List<String> allowed = List.of("comment_id", "content");
		if (!allowed.contains(sortBy)) {
			sortBy = "comment_id";
		}

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CommentDTO> pageData = service.getAllComment(pageable);

		if (pageData.isEmpty()) {
			throw new DataisEmptyException("No comments Present");
		}

		return pageData.getContent(); // empty list if no data
	}

	// Get Comment ID
	@GetMapping("/getCommentId/{id}")
	@Cacheable(value = "comment", key = "#comment_id")
	public CommentDTO getCommentId(@PathVariable("id") Long comment_id) throws Exception {
		CommentDTO comment = service.getCommentbyid(comment_id);

		if (comment == null) {
			throw new DataisEmptyException("Comment not Present with id: " + comment_id);
		}

		return comment;
	}

	// Update Comment
	@PutMapping("/updateComment/{id}")
	@CachePut(value = "comment", key = "#comment_id")
	public CommentDTO updateComment(@PathVariable("id") Long comment_id, @RequestBody CommentDTO comment)
			throws Exception {
		CommentDTO updated = service.updateComment(comment_id, comment);

		if (updated == null) {
			throw new DataisEmptyException(
					"Comment or User not Present. Comment id: " + comment_id + ", User id: " + comment.getId());
		}

		return updated;
	}

	// Delete Comment
	@DeleteMapping("/deletComment/{id}")
	@CacheEvict(value = "comment", key = "#commnet_id")
	public String deleteComment(@PathVariable("id") Long commnet_id) throws DataisEmptyException {
		boolean deleted = service.deleteComment(commnet_id);

		if (!deleted) {
			throw new DataisEmptyException("Comment not found with id: " + commnet_id);
		}

		return "Comment deleted successfully with id: " + commnet_id;
	}
}