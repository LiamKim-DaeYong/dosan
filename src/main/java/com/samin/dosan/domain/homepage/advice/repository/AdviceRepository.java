package com.samin.dosan.domain.homepage.advice.repository;

import com.samin.dosan.domain.homepage.advice.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long>, AdviceRepositoryQueryDsl {
}
