package com.samin.dosan.domain.history.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.history.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryRepositoryQueryDsl {
    Page<History> findAll(SearchParam searchParam, Pageable pageable);
}
