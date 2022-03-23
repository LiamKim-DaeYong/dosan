package com.samin.dosan.domain.setting.educator_code.repository;

import com.samin.dosan.domain.setting.educator_code.EducatorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducatorCodeRepository extends JpaRepository<EducatorCode, Long>, EducatorCodeRepositoryQueryDsl {
}
