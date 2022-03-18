package com.samin.dosan.domain.homepage.advice;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.advice.repository.AdviceRepository;
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
public class AdviceService {

    private final AdviceRepository adviceRepository;

    public Page<Advice> findAll(SearchParam searchParam, Pageable pageable) {
        return adviceRepository.findAll(searchParam, pageable);
    }

    public Advice findById(Long id) {
        return adviceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Advice advice) {
        return adviceRepository.save(advice).getId();
    }

    @Transactional
    public void check(Long id) {
        findById(id).updateStatus();
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
