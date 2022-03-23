package com.samin.dosan.web.controller.admin.user;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeService;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
import com.samin.dosan.domain.user.employees.Employee;
import com.samin.dosan.domain.user.employees.EmployeeService;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeCodeService employeeCodeService;
    private final FormerJobCodeService formerJobCodeService;

    @ModelAttribute("genders")
    public Gender[] genders() { return Gender.values(); }

    @ModelAttribute("employeeTypes")
    public List<EmployeeCode> employeeCodeList() { return employeeCodeService.findAllTypes(); }

    @ModelAttribute("formerJobCodes")
    public List<FormerJobCode> formerJobCodes() { return formerJobCodeService.findAllList(); }

    @GetMapping
    public String mainView(@RequestParam(name = "employeeType", required = false) Long employeeCodeId,
                           @ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {

        Page<Employee> result = employeeService.findAll(searchParam, employeeCodeId, pageable);
        model.addAttribute("result", result);

        return "admin/user/employees/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute("employee") EmployeeSave employeeSave) {
        return "admin/user/employees/addView";
    }
}
