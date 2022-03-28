package com.samin.dosan.domain.training.inquiry_records.repository;

import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRecordsRepository extends JpaRepository<TrainingInquiryRecords, Long>, InquiryRecordsRepositoryQueryDsl {
}
