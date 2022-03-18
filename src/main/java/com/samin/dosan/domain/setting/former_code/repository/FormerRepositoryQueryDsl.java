package com.samin.dosan.domain.setting.former_code.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_code.FormerCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FormerRepositoryQueryDsl {
    Page<FormerCode> findAll(SearchParam searchParam, Pageable pageable);

    List<FormerCode> findByFormerName(FormerCode formerCode);

    List<FormerCode> findAllList();
}
