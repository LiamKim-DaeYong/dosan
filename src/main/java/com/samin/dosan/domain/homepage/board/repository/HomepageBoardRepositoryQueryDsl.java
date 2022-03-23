package com.samin.dosan.domain.homepage.board.repository;

import com.samin.dosan.core.code.homepage.BoardType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.board.HomepageBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomepageBoardRepositoryQueryDsl {

    Page<HomepageBoard> findAll(SearchParam searchParam, Pageable pageable, BoardType boardType);
}
