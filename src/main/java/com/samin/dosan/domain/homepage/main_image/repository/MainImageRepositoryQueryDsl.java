package com.samin.dosan.domain.homepage.main_image.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.main_image.MainImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MainImageRepositoryQueryDsl {
    Page<MainImage> findAll(SearchParam searchParam, Pageable pageable);
}
