package com.samin.dosan.domain.homepage.inquiry.repository;

import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryRepositoryQueryDsl {
}
