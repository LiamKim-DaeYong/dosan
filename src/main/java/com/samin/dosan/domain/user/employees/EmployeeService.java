package com.samin.dosan.domain.user.employees;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.educator.entity.Educator;
import com.samin.dosan.domain.user.employees.entity.Employee;
import com.samin.dosan.domain.user.employees.repository.EmployeeRepository;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.repository.UserRepository;
import com.samin.dosan.web.dto.user.employee.EmployeeSave;
import com.samin.dosan.web.dto.user.employee.EmployeeSearch;
import com.samin.dosan.web.dto.user.employee.EmployeeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<EmployeeSearch> findAllList() {
        return employeeRepository.findAllList();
    }

    public Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable) {
        return employeeRepository.findAll(searchParam, employeeCodeId, pageable);
    }

    public Page<Employee> findStatus(SearchParam searchParam, Pageable pageable, String leaveType) {
        return employeeRepository.findStatus(searchParam, pageable, leaveType);
    }

    public Employee findByUserId(String userId) {
        return employeeRepository.findByUserId(userId);
    }

    @Transactional
    public String save(EmployeeSave saveData) {
        // User Login 정보
        User user = User.of(saveData.getUserId(), saveData.getPassword(), saveData.getUserNm(), Role.ROLE_EMPLOYEE);
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);

        // 임직원 정보
        Employee employee = Employee.of(saveData, saveUser);
        employeeRepository.save(employee);

        return user.getUserId();
    }

    @Transactional
    public void update(String userId, EmployeeUpdate updateData) {
        User user = userRepository.findById(userId).get();
        user.updateUserNm(updateData.getUserNm());

        Employee employee = findByUserId(userId);
        employee.update(updateData);
    }

    @Transactional
    public void leave(String userId, String leaveType) {
        User user = userRepository.findById(userId).get();
        user.leave();

        Employee employee = findByUserId(userId);
        employee.leave(leaveType);
    }

    @Transactional
    public void reinstate(String userId) {
        Employee employee = findByUserId(userId);
        employee.reinstate();
    }
}
