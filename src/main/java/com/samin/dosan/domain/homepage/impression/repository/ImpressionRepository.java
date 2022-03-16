package com.samin.dosan.domain.homepage.impression.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.impression.Impression;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpressionRepository extends BaseJpaQueryDSLRepository<Impression, Long> {
}
