package com.IntBuddy.IntBuddy.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.IntBuddy.IntBuddy.Entity.ExperianceEntity;

@Repository

public interface ExperianceRepository extends JpaRepository<ExperianceEntity, Long> {

	// search by company name (partial match)
	List<ExperianceEntity> findByCompanyNameContainingIgnoreCase(String companyName);

	// search by position
	List<ExperianceEntity> findByPositionContainingIgnoreCase(String position,Pageable pageable);

}
