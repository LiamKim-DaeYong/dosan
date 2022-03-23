package com.samin.dosan.domain.homepage.media_archive.webtoon.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.media_archive.webtoon.Webtoon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WebtoonRepositoryQueryDsl {
    Page<Webtoon> findAll(SearchParam searchParam, Pageable pageable);
}
