package com.samin.dosan.domain.homepage.board.repository;

import com.samin.dosan.domain.homepage.board.HomepageBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomepageBoardRepository extends JpaRepository<HomepageBoard, Long>, HomepageBoardRepositoryQueryDsl {
}
