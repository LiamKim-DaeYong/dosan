package com.samin.dosan.domain.training_archive.operational.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.operational.Operational;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationalRepositoryQueryDsl {

    Page<Operational> findAll(SearchParam searchParam, Pageable pageable);
}
