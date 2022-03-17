package com.samin.dosan.domain.homepage.impression.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.impression.Impression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImpressionRepositoryQueryDsl {
    Page<Impression> findAll(SearchParam searchParam, Pageable pageable);
}
