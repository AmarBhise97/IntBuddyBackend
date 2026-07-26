package com.IntBuddy.IntBuddy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IntBuddy.IntBuddy.Entity.InterviewQuestionEntity;

public interface QuestionRepository extends JpaRepository<InterviewQuestionEntity,Long> {

}
