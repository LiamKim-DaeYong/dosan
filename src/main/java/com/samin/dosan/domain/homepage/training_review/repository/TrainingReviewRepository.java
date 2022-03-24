package com.samin.dosan.domain.homepage.training_review.repository;

import com.samin.dosan.domain.homepage.training_review.TrainingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingReviewRepository extends JpaRepository<TrainingReview, Long>, TrainingReviewRepositoryQueryDsl {
}
