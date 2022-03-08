package com.samin.dosan.domain.setting.educator.repository;

import com.samin.dosan.domain.setting.educator.EducatorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducatorCodeRepository extends JpaRepository<EducatorCode, Long>, EducatorCodeRepositoryQueryDsl {
}
