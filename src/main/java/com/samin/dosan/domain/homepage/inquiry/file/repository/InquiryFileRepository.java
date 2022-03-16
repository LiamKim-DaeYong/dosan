package com.samin.dosan.domain.homepage.inquiry.file.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.inquiry.file.InquiryFile;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryFileRepository extends BaseJpaQueryDSLRepository<InquiryFile, Long> {
}