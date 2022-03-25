package com.samin.dosan.web.controller.admin.user;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeService;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeType;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
import com.samin.dosan.domain.user.employees.entity.Employee;
import com.samin.dosan.domain.user.employees.EmployeeService;
import com.samin.dosan.web.dto.user.employee.EmployeeSave;
import com.samin.dosan.web.dto.user.employee.EmployeeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeCodeService employeeCodeService;
    private final FormerJobCodeService formerJobCodeService;

    @GetMapping
    public String mainView(@RequestParam(name = "employeeType", required = false) Long employeeCodeId,
                           @ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {

        Page<Employee> result = employeeService.findAll(searchParam, employeeCodeId, pageable);
        model.addAttribute("result", result);

        return "admin/user/employees/mainView";
    }

    @GetMapping("/{userId}/detail")
    public String detailView(@PathVariable String userId, Model model) {
        Employee employee = employeeService.findByUserId(userId);
        model.addAttribute("employee", employee);

        return "admin/user/employees/detailView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute("employee") EmployeeSave employeeSave) {
        return "admin/user/employees/addView";
    }

    @GetMapping("/{userId}/edit")
    public String editView(@PathVariable String userId, Model model) {
        Employee employee = employeeService.findByUserId(userId);
        EmployeeUpdate employeeUpdate = EmployeeUpdate.fromEntity(employee);

        model.addAttribute("employee", employeeUpdate);

        return "admin/user/employees/editView";
    }

    /*================== 공통코드 ==================*/
    @ModelAttribute("genders")
    public Gender[] genders() { return Gender.values(); }

    @ModelAttribute("formerJobCodes")
    public List<FormerJobCode> formerJobCodes() { return formerJobCodeService.findAllList(); }

    @ModelAttribute("employeeTypes")
    public List<EmployeeCode> employeeTypes() {
        return employeeCodeService.findAll(EmployeeCodeType.TYPE);
    }

    @ModelAttribute("employeePositions")
    public List<EmployeeCode> employeePositions() {
        return employeeCodeService.findAll(EmployeeCodeType.POSITION);
    }

    @ModelAttribute("employeeRanks")
    public List<EmployeeCode> employeeRanks() {
        return employeeCodeService.findAll(EmployeeCodeType.RANK);
    }

    @ModelAttribute("employeeSteps")
    public List<EmployeeCode> employeeSteps() {
        return employeeCodeService.findAll(EmployeeCodeType.STEP);
    }

    @ModelAttribute("employeeDepartments")
    public List<EmployeeCode> employeeDepartments() {
        return employeeCodeService.findAll(EmployeeCodeType.DEPARTMENT);
    }
}
