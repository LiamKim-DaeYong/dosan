package com.samin.dosan.domain.setting.employees.repository;

import com.samin.dosan.domain.setting.employees.EmployeesCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesCodeRepository extends JpaRepository<EmployeesCode, Long>, EmployeesCodeRepositoryQueryDsl {
    EmployeesCode findByCode(String code);
}
