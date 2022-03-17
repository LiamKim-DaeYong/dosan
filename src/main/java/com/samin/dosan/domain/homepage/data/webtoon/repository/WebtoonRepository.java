package com.samin.dosan.domain.homepage.data.webtoon.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.data.webtoon.Webtoon;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends BaseJpaQueryDSLRepository<Webtoon, Long>, WebtoonRepositoryQueryDsl {
}
