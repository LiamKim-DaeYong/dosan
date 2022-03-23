package com.samin.dosan.domain.setting.employee_code.repository;

import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCodeRepository extends JpaRepository<EmployeeCode, Long>, EmployeeCodeRepositoryQueryDsl {
    EmployeeCode findByCode(String code);
}
