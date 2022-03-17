package com.samin.dosan.domain.homepage.impression.repository;

import com.samin.dosan.domain.homepage.impression.Impression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpressionRepository extends JpaRepository<Impression, Long>, ImpressionRepositoryQueryDsl {
}
