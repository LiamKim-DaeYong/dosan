package com.samin.dosan.domain.homepage.inquiry.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends BaseJpaQueryDSLRepository<Inquiry, Long> {
}
