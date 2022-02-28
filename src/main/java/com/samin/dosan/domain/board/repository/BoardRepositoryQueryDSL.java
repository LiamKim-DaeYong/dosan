package com.samin.dosan.domain.board.repository;

import com.samin.dosan.domain.board.Board;
import com.samin.dosan.web.param.SearchParam;

import java.util.List;

public interface BoardRepositoryQueryDSL {
    List<Board> findAll(SearchParam searchParam);
}
