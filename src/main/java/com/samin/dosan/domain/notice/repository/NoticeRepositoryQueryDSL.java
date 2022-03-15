package com.samin.dosan.domain.notice.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryQueryDSL {
    Page<Notice> findAll(SearchParam searchParam, Pageable pageable);
}
