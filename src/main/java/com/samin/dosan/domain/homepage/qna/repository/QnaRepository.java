package com.samin.dosan.domain.homepage.qna.repository;

import com.samin.dosan.domain.homepage.qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long>, QnaRepositoryQueryDsl {
}
