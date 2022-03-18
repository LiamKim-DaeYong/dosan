package com.samin.dosan.domain.homepage.data.promotion;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.promotion.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public Page<Promotion> findAll(SearchParam searchParam, Pageable pageable) {
        return promotionRepository.findAll(searchParam, pageable);
    }

    public Promotion findById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Promotion saveData) {
        return promotionRepository.save(saveData).getId();
    }

    @Transactional
    public Long update(Long id, Promotion updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
