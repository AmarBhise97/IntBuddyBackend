package com.IntBuddy.IntBuddy.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.IntBuddy.IntBuddy.Entity.ExperianceEntity;

@Repository

public interface ExperianceRepository extends JpaRepository<ExperianceEntity, Long> {

	// search by company name (partial match)
	List<ExperianceEntity> findByCompanyNameContainingIgnoreCase(String companyName);

//	// search by position
//	Page<ExperianceEntity> findByPositionContainingIgnoreCase(String position,Pageable pageable);

	@Query("SELECT e FROM ExperianceEntity e WHERE LOWER(e.position) LIKE LOWER(CONCAT('%', :position, '%'))")
	Page<ExperianceEntity> searchByPosition(@Param("position") String position, Pageable pageable);

}
