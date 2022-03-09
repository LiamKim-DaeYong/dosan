package com.samin.dosan.web.api.setting;

import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.employees.EmployeesCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setting/employees/{type}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EmployeesCodeApiController {

    private final EmployeesCodeService employeesCodeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody EmployeesCode saveData) {
        employeesCodeService.save(saveData.init(type));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity edit(@Valid @RequestBody EmployeesCode updateData) {
        employeesCodeService.update(updateData.getId(), updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        employeesCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
