package com.IntBuddy.IntBuddy.Repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.IntBuddy.IntBuddy.Entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	Page<CommentEntity> findByContentContainingIgnoreCase(String content, Pageable pageable);
}
