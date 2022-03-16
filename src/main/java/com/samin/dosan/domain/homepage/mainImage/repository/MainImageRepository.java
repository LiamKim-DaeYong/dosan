package com.samin.dosan.domain.homepage.mainImage.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.mainImage.MainImage;
import org.springframework.stereotype.Repository;

@Repository
public interface MainImageRepository extends BaseJpaQueryDSLRepository<MainImage, Long> {
}
