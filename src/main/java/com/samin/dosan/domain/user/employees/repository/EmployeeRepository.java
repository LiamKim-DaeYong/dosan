package com.samin.dosan.domain.user.employees.repository;

import com.samin.dosan.domain.user.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryQueryDsl {
}
