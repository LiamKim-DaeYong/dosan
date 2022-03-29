package com.samin.dosan.domain.training.program.school.repository;

import com.samin.dosan.domain.training.program.school.SchoolProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolProgramRepository extends JpaRepository<SchoolProgram, Long>, SchoolProgramRepositoryQueryDsl {
}
