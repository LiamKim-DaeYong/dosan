package com.samin.dosan.domain.homepage.commonfile.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.commonfile.CommonFile;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonFileRepository extends BaseJpaQueryDSLRepository<CommonFile, Long> {
}
