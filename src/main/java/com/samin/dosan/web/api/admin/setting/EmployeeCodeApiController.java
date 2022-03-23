package com.samin.dosan.web.api.admin.setting;

import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/setting/employee-code/{type}")
public class EmployeeCodeApiController {

    private final EmployeeCodeService employeeCodeService;

    @PostMapping
    public ResponseEntity save(@PathVariable String type, @Valid @RequestBody EmployeeCode saveData) {
        employeeCodeService.save(EmployeeCode.of(saveData, type));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @Valid @RequestBody EmployeeCode updateData) {
        employeeCodeService.update(id, updateData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<Long> ids) {
        employeeCodeService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
