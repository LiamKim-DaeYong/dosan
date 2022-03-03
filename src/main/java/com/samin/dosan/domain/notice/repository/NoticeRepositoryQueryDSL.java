package com.samin.dosan.domain.notice.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.notice.Notice;

import java.util.List;

public interface NoticeRepositoryQueryDSL {
    List<Notice> findAll(SearchParam searchParam);
}
