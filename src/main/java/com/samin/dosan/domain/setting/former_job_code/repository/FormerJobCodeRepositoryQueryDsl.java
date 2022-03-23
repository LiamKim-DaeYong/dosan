package com.samin.dosan.domain.setting.former_job_code.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FormerJobCodeRepositoryQueryDsl {
    Page<FormerJobCode> findAll(SearchParam searchParam, Pageable pageable);

    List<FormerJobCode> findAllList();
}
