package com.samin.dosan.domain.user.employees;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.employees.repository.EmployeeRepository;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable) {
        return null;
    }

    public Employee findById(String userId) {
        return employeeRepository.findByUserId(userId);
    }

    @Transactional
    public String save(EmployeeSave saveData) {
        saveData.setPassword(passwordEncoder.encode(saveData.getPassword()));
        employeeRepository.save(Employee.of(saveData));

        return Employee.of(saveData).getUser().getUserId();
    }
}
