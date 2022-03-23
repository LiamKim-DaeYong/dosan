package com.samin.dosan.domain.homepage.media_archive.promotional_video.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionalVideoRepositoryQueryDsl {
    Page<PromotionalVideo> findAll(SearchParam searchParam, Pageable pageable);
}
