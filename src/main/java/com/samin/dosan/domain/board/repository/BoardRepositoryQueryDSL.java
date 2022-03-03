package com.samin.dosan.domain.board.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.board.Board;

import java.util.List;

public interface BoardRepositoryQueryDSL {
    List<Board> findAll(SearchParam searchParam);
}
