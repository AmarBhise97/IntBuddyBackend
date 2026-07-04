package com.IntBuddy.IntBuddy.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CommentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comment_id;
	private String content;

	@ManyToOne
	@JoinColumn(name = "id")
	private UserEntity user;

//	@ManyToOne
//	@JoinColumn(name= "experiance_ID")
//	private ExperianceEntity exp;
//	

	public Long getComment_id() {
		return comment_id;
	}

	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
//	public ExperianceEntity getExp() {
//		return exp;
//	}
//	public void setExp(ExperianceEntity exp) {
//		this.exp = exp;
//	}
//	
}
