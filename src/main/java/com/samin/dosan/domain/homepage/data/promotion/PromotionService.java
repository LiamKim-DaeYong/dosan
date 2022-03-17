package com.samin.dosan.domain.homepage.data.promotion;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.promotion.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public Page<Promotion> findAll(SearchParam searchParam, Pageable pageable) {
        return promotionRepository.findAll(searchParam, pageable);
    }

    @Transactional
    public Long save(Promotion promotion) {
        Long id = promotionRepository.save(promotion).getId();
        return id;
    }
}
