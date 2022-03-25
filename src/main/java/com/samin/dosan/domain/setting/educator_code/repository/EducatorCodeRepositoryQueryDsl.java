package com.samin.dosan.domain.setting.educator_code.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator_code.EducatorCode;
import com.samin.dosan.domain.setting.educator_code.EducatorCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducatorCodeRepositoryQueryDsl {
    Page<EducatorCode> findAll(SearchParam searchParam, EducatorCodeType educatorCodeType, Pageable pageable);

    List<EducatorCode> findAll(EducatorCodeType educatorCodeType);
}
