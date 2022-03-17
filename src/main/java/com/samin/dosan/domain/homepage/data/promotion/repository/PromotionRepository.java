package com.samin.dosan.domain.homepage.data.promotion.repository;

import com.samin.dosan.domain.homepage.data.promotion.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>, PromotionRepositoryQueryDsl {
}
