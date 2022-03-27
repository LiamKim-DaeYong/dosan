package com.samin.dosan.domain.training_archive.operational.repository;

import com.samin.dosan.domain.training_archive.operational.Operational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationalRepository extends JpaRepository<Operational, Long>, OperationalRepositoryQueryDsl {
}
