package com.samin.dosan.domain.setting.former.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former.Former;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormerRepositoryQueryDsl {
    Page<Former> findAll(SearchParam searchParam, Pageable pageable);
}
