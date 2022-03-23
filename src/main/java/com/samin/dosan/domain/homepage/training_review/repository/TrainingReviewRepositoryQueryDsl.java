package com.samin.dosan.domain.homepage.training_review.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_review.TrainingReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrainingReviewRepositoryQueryDsl {
    Page<TrainingReview> findAll(SearchParam searchParam, Pageable pageable);
}
