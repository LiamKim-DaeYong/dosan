package com.samin.dosan.domain.user.employees;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.domain.user.employees.repository.EmployeeRepository;
import com.samin.dosan.domain.user.entity.User;
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
    private final UserService userService;

    public Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable) {
        return employeeRepository.findAll(searchParam, employeeCodeId, pageable);
    }

    public Employee findById(String userId) {
        return employeeRepository.findByUserId(userId);
    }

    @Transactional
    public String save(Employee employee) {
        User user = userService.save(employee.getUser());
        employeeRepository.save(employee);

        return user.getUserId();
    }
}
