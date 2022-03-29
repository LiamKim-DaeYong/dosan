package com.samin.dosan.domain.training.inquiry_records.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryRecordsService {

    private final InquiryRecordsRepository inquiryRecordsRepository;

    public Page<TrainingInquiryRecords> findAll(SearchParam searchParam, Pageable pageable) {
        return inquiryRecordsRepository.findAll(searchParam, pageable);
    }

    public TrainingInquiryRecords findById(Long id) {
        return inquiryRecordsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(TrainingInquiryRecords trainingInquiryRecords) {
        return inquiryRecordsRepository.save(trainingInquiryRecords).getId();
    }

    @Transactional
    public Long update(Long id, TrainingInquiryRecords trainingInquiryRecords) {
        findById(id).update(trainingInquiryRecords);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id ->
                findById(id).delete());
    }
}
