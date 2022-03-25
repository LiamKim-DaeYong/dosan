package com.samin.dosan.domain.user.educator.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.educator.entity.Educator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EducatorRepositoryQueryDsl {
    Page<Educator> findAll(SearchParam searchParam, Long educatorCodeId, Pageable pageable);

    Educator findByUserId(String userId);
}
