package com.samin.dosan.web.api.admin.user;

import com.samin.dosan.domain.user.employees.EmployeeService;
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
@RequestMapping({"/admin/user/employees", "/api/v1/employees"})
public class EmployeeApiController {

    private final EmployeeService employeeService;
    private final EmployeeUserIdValidator employeeUserIdValidator;

    @InitBinder("employeeSave")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(employeeUserIdValidator);
    }

    @GetMapping("/list")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(employeeService.findAllList());
    }


    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody EmployeeSave saveData) {
        String userId = employeeService.save(saveData);
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/{userId}/edit")
    public ResponseEntity update(@PathVariable String userId, @Valid @RequestBody EmployeeUpdate updateData) {
        employeeService.update(userId, updateData);
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/{userId}/{leaveType}")
    public ResponseEntity leave(@PathVariable String userId, @PathVariable String leaveType) {
        employeeService.leave(userId, leaveType);
        return ResponseEntity.ok(userId);
    }
}
