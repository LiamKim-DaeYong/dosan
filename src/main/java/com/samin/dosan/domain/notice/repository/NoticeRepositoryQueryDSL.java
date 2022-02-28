package com.samin.dosan.domain.notice.repository;

import com.samin.dosan.domain.notice.Notice;
import com.samin.dosan.web.param.SearchParam;

import java.util.List;

public interface NoticeRepositoryQueryDSL {
    List<Notice> findAll(SearchParam searchParam);
}
