package com.samin.dosan.domain.homepage.webtoon.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.webtoon.Webtoon;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends BaseJpaQueryDSLRepository<Webtoon, Long> {
}
