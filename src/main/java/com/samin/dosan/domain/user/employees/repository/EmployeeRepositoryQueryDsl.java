package com.samin.dosan.domain.user.employees.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.employees.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepositoryQueryDsl {
    Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable);

    Employee findByUserId(String userId);
}
