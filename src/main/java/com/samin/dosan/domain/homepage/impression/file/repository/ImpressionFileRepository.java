package com.samin.dosan.domain.homepage.impression.file.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.impression.file.ImpressionFile;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpressionFileRepository extends BaseJpaQueryDSLRepository<ImpressionFile, Long> {
}