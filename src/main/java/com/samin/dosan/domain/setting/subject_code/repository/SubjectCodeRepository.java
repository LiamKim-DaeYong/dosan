package com.samin.dosan.domain.setting.subject_code.repository;

import com.samin.dosan.domain.setting.subject_code.SubjectCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectCodeRepository extends JpaRepository<SubjectCode, Long>, SubjectCodeRepositoryQueryDsl {
}
