package com.samin.dosan.domain.homepage.notice.file.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.notice.file.NoticeFile;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeFileRepository extends BaseJpaQueryDSLRepository<NoticeFile, Long> {
}

