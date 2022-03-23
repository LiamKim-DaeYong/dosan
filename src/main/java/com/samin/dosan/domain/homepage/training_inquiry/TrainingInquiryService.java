package com.samin.dosan.domain.homepage.training_inquiry;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_inquiry.repository.TrainingInquiryRepository;
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
public class TrainingInquiryService {

    private final TrainingInquiryRepository trainingInquiryRepository;

    public Page<TrainingInquiry> findAll(SearchParam searchParam, Pageable pageable) {
        return trainingInquiryRepository.findAll(searchParam, pageable);
    }

    public TrainingInquiry findById(Long id) {
        return trainingInquiryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(TrainingInquiry trainingInquiry) {
        return trainingInquiryRepository.save(trainingInquiry).getId();
    }

    @Transactional
    public void check(Long id) {
        findById(id).updateStatus();
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
