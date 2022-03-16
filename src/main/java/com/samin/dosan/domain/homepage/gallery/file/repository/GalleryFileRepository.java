package com.samin.dosan.domain.homepage.gallery.file.repository;

import com.samin.dosan.core.homepage_core.BaseJpaQueryDSLRepository;
import com.samin.dosan.domain.homepage.gallery.file.GalleryFile;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryFileRepository extends BaseJpaQueryDSLRepository<GalleryFile, Long> {
}