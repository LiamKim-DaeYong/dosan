package com.samin.dosan.domain.homepage.report.file.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.report.file.ReportFile;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportFileRepository extends BaseJpaQueryDSLRepository<ReportFile, Long> {
}

