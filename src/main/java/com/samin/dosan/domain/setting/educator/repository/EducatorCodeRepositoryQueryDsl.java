package com.samin.dosan.domain.setting.educator.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducatorCodeRepositoryQueryDsl {
    Page<EducatorCode> findAll(SearchParam searchParam, EducatorCodeType educatorCodeType, Pageable pageable);

    List<EducatorCode> findAllTypes();
}
