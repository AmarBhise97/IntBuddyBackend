package com.IntBuddy.IntBuddy.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.IntBuddy.IntBuddy.DTO.CommentDTO;
import com.IntBuddy.IntBuddy.Entity.CommentEntity;
import com.IntBuddy.IntBuddy.Entity.UserEntity;
import com.IntBuddy.IntBuddy.Repository.CommentRepository;
import com.IntBuddy.IntBuddy.Repository.UserRepository;

@Service

public class CommentService {

	private CommentRepository repo;
	private UserRepository userRepo;

	public CommentService(CommentRepository repo, UserRepository userRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
	}

	// Add Comments
	public CommentEntity addcomment(CommentEntity comment) {

		return repo.save(comment);
	}

	// Search Comments
	public Page<CommentDTO> searchComments(String content, Pageable pageable) {

		Page<CommentEntity> page = repo.findByContentContainingIgnoreCase(content, pageable);

		if (page.isEmpty()) {
			return Page.empty();
		}

		return page.map(comment -> {
			CommentDTO dto = new CommentDTO();
			dto.setComment_id(comment.getComment_id());
			dto.setContent(comment.getContent());
			dto.setId(comment.getUser().getId());
			dto.setFullName(comment.getUser().getFullName());
			dto.setEmail(comment.getUser().getEmail());
			return dto;

		});
	}

	// Get All Comments
	public Page<CommentDTO> getAllComment(Pageable pageable) {

		return repo.findAll(pageable).map(comment -> {
			CommentDTO dto = new CommentDTO();
			dto.setComment_id(comment.getComment_id());
			dto.setContent(comment.getContent());
			dto.setId(comment.getUser().getId());
			dto.setFullName(comment.getUser().getFullName());
			dto.setEmail(comment.getUser().getEmail());
			return dto;
		});
	}

	// Get Comment ID
	public CommentDTO getCommentbyid(Long comment_id) {

		Optional<CommentEntity> optionalComment = repo.findById(comment_id);

		if (optionalComment.isEmpty()) {
			return null;
		}

		CommentEntity commentid = optionalComment.get();

		CommentDTO come = new CommentDTO();
		come.setId(commentid.getUser().getId());
		come.setComment_id(commentid.getComment_id());
		come.setContent(commentid.getContent());
		come.setFullName(commentid.getUser().getFullName());
		come.setEmail(commentid.getUser().getEmail());

		return come;
	}

	// Update Comment
	public CommentDTO updateComment(Long comment_id, CommentDTO dto) throws Exception {

		Optional<CommentEntity> optionalComment = repo.findById(comment_id);

		if (optionalComment.isEmpty()) {
			return null;
		}

		CommentEntity existing = optionalComment.get();

		existing.setContent(dto.getContent());

		if (dto.getId() != null) {

			Optional<UserEntity> optionalUser = userRepo.findById(dto.getId());

			if (optionalUser.isEmpty()) {
				return null;
			}

			existing.setUser(optionalUser.get());
		}

		CommentEntity saved = repo.save(existing);

		CommentDTO response = new CommentDTO();
		response.setComment_id(saved.getComment_id());
		response.setContent(saved.getContent());
		response.setId(saved.getUser().getId());
		response.setFullName(saved.getUser().getFullName());
		response.setEmail(saved.getUser().getEmail());

		return response;
	}

	// Delete Comment
	public boolean deleteComment(Long id) {

		Optional<CommentEntity> optionalComment = repo.findById(id);

		if (optionalComment.isEmpty()) {
			return false;
		}

		repo.delete(optionalComment.get());
		return true;
	}

}
