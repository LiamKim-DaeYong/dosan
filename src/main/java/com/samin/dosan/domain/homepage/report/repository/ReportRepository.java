package com.samin.dosan.domain.homepage.report.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.report.Report;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends BaseJpaQueryDSLRepository<Report, Long> {
}
