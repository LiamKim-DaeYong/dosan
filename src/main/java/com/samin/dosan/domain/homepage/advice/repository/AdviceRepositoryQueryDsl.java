package com.samin.dosan.domain.homepage.advice.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.advice.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdviceRepositoryQueryDsl {
    Page<Advice> findAll(SearchParam searchParam, Pageable pageable);
}
