package com.samin.dosan.domain.homepage.board.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.board.HomepageBoard;
import com.samin.dosan.domain.homepage.type.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomepageBoardRepositoryQueryDsl {

    Page<HomepageBoard> findAll(SearchParam searchParam, Pageable pageable, BoardType boardType);
}
