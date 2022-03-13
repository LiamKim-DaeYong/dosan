package com.samin.dosan.domain.board.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryQueryDSL {
    Page<Board> findAll(SearchParam searchParam, Pageable pageable);
}
