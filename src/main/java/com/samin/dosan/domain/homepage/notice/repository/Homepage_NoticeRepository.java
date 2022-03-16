package com.samin.dosan.domain.homepage.notice.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.notice.Homepage_Notice;
import org.springframework.stereotype.Repository;

@Repository
public interface Homepage_NoticeRepository extends BaseJpaQueryDSLRepository<Homepage_Notice, Long> {
}