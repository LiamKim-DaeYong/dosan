package com.samin.dosan.domain.homepage.data.promotion.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.promotion.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionRepositoryQueryDsl {
    Page<Promotion> findAll(SearchParam searchParam, Pageable pageable);
}
