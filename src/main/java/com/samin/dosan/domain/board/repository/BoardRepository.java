package com.samin.dosan.domain.board.repository;

import com.samin.dosan.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQueryDsl {
}
