package com.samin.dosan.domain.homepage.advice.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.advice.Advice;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends BaseJpaQueryDSLRepository<Advice, Long> {
}
