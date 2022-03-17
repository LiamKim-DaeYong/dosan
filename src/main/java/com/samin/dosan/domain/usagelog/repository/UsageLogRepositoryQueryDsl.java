package com.samin.dosan.domain.usagelog.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.usagelog.UsageLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsageLogRepositoryQueryDsl {
    Page<UsageLog> findAll(SearchParam searchParam, Pageable pageable);
}
