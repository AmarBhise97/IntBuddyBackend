package com.IntBuddy.IntBuddy.Service;

import java.util.List;
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

	public CommentEntity addcomment(CommentEntity comment) {

		return repo.save(comment);
	}

	public Page<CommentDTO> searchComments(String content, Pageable pageable) {

		return repo.findByContentContainingIgnoreCase(content, pageable).map(comment -> {
			CommentDTO dto = new CommentDTO();
			dto.setComment_id(comment.getComment_id());
			dto.setContent(comment.getContent());
			dto.setId(comment.getUser().getId());
			dto.setName(comment.getUser().getName());
			dto.setEmail(comment.getUser().getEmail());
			return dto;
		});
	}

	public Page<CommentDTO> getAllComment(Pageable pageable) {

		return repo.findAll(pageable).map(comment -> {
			CommentDTO dto = new CommentDTO();
			dto.setComment_id(comment.getComment_id());
			dto.setContent(comment.getContent());
			dto.setId(comment.getUser().getId());
			dto.setName(comment.getUser().getName());
			dto.setEmail(comment.getUser().getEmail());
			return dto;
		});
	}

	public CommentDTO getCommentbyid(Long comment_id) throws Exception {
		return repo.findById(comment_id).map((commentid) -> {

			CommentDTO come = new CommentDTO();
			come.setId(commentid.getUser().getId());
			come.setComment_id(commentid.getComment_id());
			come.setContent(commentid.getContent());
			come.setName(commentid.getUser().getName());
			come.setEmail(commentid.getUser().getEmail());
			return come;
		}).orElseThrow(() -> new Exception("invalid id " + comment_id));

	}

	public CommentDTO updateComment(Long comment_id, CommentDTO dto) throws Exception {

		CommentEntity existing = repo.findById(comment_id)
				.orElseThrow(() -> new Exception("Invalid comment id " + comment_id));

		// update comment fields
		existing.setContent(dto.getContent());

		// update user if needed
		if (dto.getId() != null) {
			UserEntity user = userRepo.findById(dto.getId())
					.orElseThrow(() -> new Exception("Invalid user id " + dto.getId()));
			existing.setUser(user);
		}

		// save updated entity
		CommentEntity saved = repo.save(existing);

		// convert back to DTO
		CommentDTO response = new CommentDTO();
		response.setComment_id(saved.getComment_id());
		response.setContent(saved.getContent());
		response.setId(saved.getUser().getId());
		response.setName(saved.getUser().getName());
		response.setEmail(saved.getUser().getEmail());

		return response;
	}

	public String deleteComment(Long comment_id) {
		repo.deleteById(comment_id);
		return "Comment Delete Succesfully.... " + comment_id;

	}

}
