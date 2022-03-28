package com.samin.dosan.domain.training_archive.branch.repository;

import com.samin.dosan.domain.training_archive.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>, BranchRepositoryQueryDsl {
}
