package com.samin.dosan.domain.setting.employee.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeesCodeRepositoryQueryDsl {
    Page<EmployeeCode> findAll(SearchParam searchParam, EmployeeCodeType employeeCodeType, Pageable pageable);

    List<EmployeeCode> findAllTypes();

    List<EmployeeCode> findAllPosition();

    List<EmployeeCode> findAllRank();

    List<EmployeeCode> findAllStep();

    List<EmployeeCode> findAllDepartment();
}
