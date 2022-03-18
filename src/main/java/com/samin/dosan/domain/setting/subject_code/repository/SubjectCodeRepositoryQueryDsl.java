package com.samin.dosan.domain.setting.subject_code.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.subject_code.SubjectCode;
import com.samin.dosan.domain.setting.subject_code.SubjectCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectCodeRepositoryQueryDsl {
    Page<SubjectCode> findAll(SearchParam searchParam, SubjectCodeType subjectCodeType, Pageable pageable);
}
