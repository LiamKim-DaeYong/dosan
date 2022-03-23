package com.samin.dosan.domain.homepage.media_archive.promotional_video.repository;

import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionalVideoRepository extends JpaRepository<PromotionalVideo, Long>, PromotionalVideoRepositoryQueryDsl {
}
