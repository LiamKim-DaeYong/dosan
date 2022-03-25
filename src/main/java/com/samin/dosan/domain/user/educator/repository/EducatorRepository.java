package com.samin.dosan.domain.user.educator.repository;

import com.samin.dosan.domain.user.educator.entity.Educator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducatorRepository extends JpaRepository<Educator, Long>, EducatorRepositoryQueryDsl {
}
