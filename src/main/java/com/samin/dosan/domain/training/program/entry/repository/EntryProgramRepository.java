package com.samin.dosan.domain.training.program.entry.repository;

import com.samin.dosan.domain.training.program.entry.entity.EntryProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryProgramRepository extends JpaRepository<EntryProgram, Long>, EntryProgramRepositoryQueryDsl {
}
