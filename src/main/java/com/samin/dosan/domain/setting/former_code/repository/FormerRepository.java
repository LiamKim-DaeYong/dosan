package com.samin.dosan.domain.setting.former_code.repository;

import com.samin.dosan.domain.setting.former_code.FormerCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormerRepository extends JpaRepository<FormerCode, Long>, FormerRepositoryQueryDsl {
}
