package com.samin.dosan.web.api.admin.user;

import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.domain.user.employees.EmployeeService;
import com.samin.dosan.domain.user.employees.entity.Employee;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.api.admin.user.validator.EmployeeUserIdValidator;
import com.samin.dosan.web.dto.user.employee.EmployeeSave;
import com.samin.dosan.web.dto.user.employee.EmployeeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user/employees")
public class EmployeeApiController {

    private final UserService userService;
    private final EmployeeService employeeService;
    private final EmployeeUserIdValidator employeeUserIdValidator;

    @InitBinder("employeeSave")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(employeeUserIdValidator);
    }

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody EmployeeSave saveData) {
        User user = User.of(saveData.getUserId(), saveData.getPassword(), saveData.getUserNm(), Role.ROLE_EMPLOYEE);
        userService.save(user);

        Employee employee = Employee.of(saveData, user);
        employeeService.save(employee);

        return ResponseEntity.ok(user.getUserId());
    }

    @PutMapping("/{userId}/edit")
    public ResponseEntity update(@PathVariable String userId, @Valid @RequestBody EmployeeUpdate updateData) {
        userService.update(userId, updateData.getUserNm());
        employeeService.update(userId, updateData);
        return ResponseEntity.ok(userId);
    }
}
