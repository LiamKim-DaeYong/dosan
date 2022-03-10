package com.samin.dosan.domain.setting.employees.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.employees.EmployeesCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeesCodeRepositoryQueryDsl {
    Page<EmployeesCode> findAll(SearchParam searchParam, EmployeesCodeType employeesCodeType, Pageable pageable);

    List<EmployeesCode> findAllTypes();
}
