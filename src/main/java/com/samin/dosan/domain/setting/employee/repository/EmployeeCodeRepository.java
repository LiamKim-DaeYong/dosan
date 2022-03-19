package com.samin.dosan.domain.setting.employee.repository;

import com.samin.dosan.domain.setting.employee.EmployeeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCodeRepository extends JpaRepository<EmployeeCode, Long>, EmployeesCodeRepositoryQueryDsl {
    EmployeeCode findByCode(String code);
}
