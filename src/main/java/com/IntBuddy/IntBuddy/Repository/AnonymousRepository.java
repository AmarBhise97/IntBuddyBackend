package com.IntBuddy.IntBuddy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.IntBuddy.IntBuddy.Entity.AnonymousExperienceEntity;

public interface AnonymousRepository extends JpaRepository<AnonymousExperienceEntity,Long> {

}
