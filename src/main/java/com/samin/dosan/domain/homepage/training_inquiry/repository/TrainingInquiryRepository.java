package com.samin.dosan.domain.homepage.training_inquiry.repository;

import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingInquiryRepository extends JpaRepository<TrainingInquiry, Long>, TrainingInquiryRepositoryQueryDsl {
}
