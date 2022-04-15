package com.samin.dosan.domain.training.program.entry.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.program.entry.entity.EntryProgram;
import com.samin.dosan.web.dto.training.program.entry.EntryProgramList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntryProgramRepositoryQueryDsl {
    Page<EntryProgramList> findAll(SearchParam searchParam, Pageable pageable);

    void deleteByInquiryRecordId(Long inquiryRecordId);

    EntryProgram findByProgramId(Long id);
}
