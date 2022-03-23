package com.samin.dosan.domain.homepage.training_inquiry.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrainingInquiryRepositoryQueryDsl {
    Page<TrainingInquiry> findAll(SearchParam searchParam, Pageable pageable);
}
