package com.samin.dosan.domain.training_archive.consultation.repository;

import com.samin.dosan.domain.training_archive.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long>, ConsultationRepositoryQueryDsl {
}
