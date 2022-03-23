package com.samin.dosan.domain.homepage.training_review;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_review.repository.TrainingReviewRepository;
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
public class TrainingReviewService {

    private final TrainingReviewRepository trainingReviewRepository;

    public Page<TrainingReview> findAll(SearchParam searchParam, Pageable pageable) {
        return trainingReviewRepository.findAll(searchParam, pageable);
    }

    public TrainingReview findById(Long id) {
        return trainingReviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }

    @Transactional
    public Long save(TrainingReview trainingReview) {
        return trainingReviewRepository.save(trainingReview).getId();
    }
}
