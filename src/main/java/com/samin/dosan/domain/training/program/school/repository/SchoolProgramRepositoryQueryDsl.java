package com.samin.dosan.domain.training.program.school.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.program.school.SchoolProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolProgramRepositoryQueryDsl {
    Page<SchoolProgram> findAll(SearchParam searchParam, Pageable pageable);
}
