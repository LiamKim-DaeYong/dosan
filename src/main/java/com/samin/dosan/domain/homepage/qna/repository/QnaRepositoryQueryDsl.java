package com.samin.dosan.domain.homepage.qna.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.qna.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaRepositoryQueryDsl {

    Page<Qna> findAll(SearchParam searchParam, Pageable pageable);
}
