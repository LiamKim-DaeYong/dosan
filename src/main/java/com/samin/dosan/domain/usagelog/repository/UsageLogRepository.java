package com.samin.dosan.domain.usagelog.repository;

import com.samin.dosan.domain.usagelog.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageLogRepository extends JpaRepository<UsageLog, Long>, UsageLogRepositoryQueryDsl {
}
