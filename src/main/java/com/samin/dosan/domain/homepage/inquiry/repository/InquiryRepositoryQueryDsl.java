package com.samin.dosan.domain.homepage.inquiry.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryRepositoryQueryDsl {
    Page<Inquiry> findAll(SearchParam searchParam, Pageable pageable);
}
