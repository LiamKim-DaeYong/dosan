package com.samin.dosan.domain.training_archive.consultation.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.consultation.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultationRepositoryQueryDsl {
    Page<Consultation> findAll(SearchParam searchParam, Pageable pageable);
}
