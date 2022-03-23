package com.samin.dosan.web.api.admin.user;

import com.samin.dosan.domain.user.employees.EmployeeService;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user/employees")
public class EmployeeApiController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity save(@RequestBody EmployeeSave saveData) {
        employeeService.save(saveData);
        return ResponseEntity.ok().build();
    }
}
