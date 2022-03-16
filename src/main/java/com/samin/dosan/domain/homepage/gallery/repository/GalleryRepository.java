package com.samin.dosan.domain.homepage.gallery.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.gallery.Gallery;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends BaseJpaQueryDSLRepository<Gallery, Long> {
}

