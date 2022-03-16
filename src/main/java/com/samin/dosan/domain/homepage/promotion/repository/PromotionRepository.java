package com.samin.dosan.domain.homepage.promotion.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.promotion.Promotion;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends BaseJpaQueryDSLRepository<Promotion, Long> {
}
