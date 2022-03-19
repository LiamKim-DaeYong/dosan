package com.samin.dosan.domain.setting.former_job_code.repository;

import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormerJobCodeRepository extends JpaRepository<FormerJobCode, Long>, FormerJobCodeRepositoryQueryDsl {
}
