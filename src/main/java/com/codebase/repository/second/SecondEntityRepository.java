package com.codebase.repository.second;

import com.codebase.model.second.entity.SecondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondEntityRepository extends JpaRepository<SecondEntity, Integer> {
}