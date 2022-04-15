package com.samin.dosan.domain.user.employees.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.employees.entity.Employee;
import com.samin.dosan.web.dto.user.employee.EmployeeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepositoryQueryDsl {
    Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable);

    Page<Employee> findStatus(SearchParam searchParam, Pageable pageable, String leaveType);

    List<EmployeeSearch> findAllList();

    Employee findByUserId(String userId);
}
