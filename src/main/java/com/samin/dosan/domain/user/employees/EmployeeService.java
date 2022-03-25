package com.samin.dosan.domain.user.employees;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.employees.entity.Employee;
import com.samin.dosan.domain.user.employees.repository.EmployeeRepository;
import com.samin.dosan.web.dto.user.employee.EmployeeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable) {
        return employeeRepository.findAll(searchParam, employeeCodeId, pageable);
    }

    public Employee findByUserId(String userId) {
        return employeeRepository.findByUserId(userId);
    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(String userId, EmployeeUpdate updateData) {
        Employee employee = findByUserId(userId);
        employee.update(updateData);
    }
}
