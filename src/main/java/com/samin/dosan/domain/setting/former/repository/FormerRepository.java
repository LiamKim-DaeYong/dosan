package com.samin.dosan.domain.setting.former.repository;

import com.samin.dosan.domain.setting.former.Former;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormerRepository extends JpaRepository<Former, Long>, FormerRepositoryQueryDsl {
}
