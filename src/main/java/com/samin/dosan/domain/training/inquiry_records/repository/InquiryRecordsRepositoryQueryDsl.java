package com.samin.dosan.domain.training.inquiry_records.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryRecordsRepositoryQueryDsl {

    Page<TrainingInquiryRecords> findAll(SearchParam searchParam, Pageable pageable);
}
