package com.samin.dosan.web.controller.admin;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeService;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeService;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private final UserService userService;
    private final EmployeeCodeService employeeCodeService;
    private final EducatorCodeService educatorCodeService;
    private final FormerJobCodeService formerJobCodeService;

    @ModelAttribute("employeesTypes")
    public List<EmployeeCode> employeesTypes() {
        return employeeCodeService.findAllTypes();
    }

    @ModelAttribute("educatorTypes")
    public List<EducatorCode> educatorTypes() {
        return educatorCodeService.findAllTypes();
    }

    @ModelAttribute("formers")
    public List<FormerJobCode> formers() {
        return formerJobCodeService.findAllList();
    }

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    /*==================  임직원 ==================*/
    @GetMapping({"/employees", "/employees/{employeesType}"})
    public String employees(@PathVariable(required = false) Long employeesType, @ModelAttribute SearchParam searchParam,
                            Pageable pageable, Model model) {
        Page<User> result = userService.findAllEmployees(searchParam, employeesType, pageable);
        model.addAttribute("result", result);

        return "user/employees/employees";
    }

    @GetMapping("/employees/add")
    public String addEmployees(@ModelAttribute("employee") EmployeeSave employee, Model model) {

//        model.addAttribute("positions", employeesCodeService.findAllPosition());
//        model.addAttribute("ranks", employeesCodeService.findAllRank());
//        model.addAttribute("steps", employeesCodeService.findAllStep());
//        model.addAttribute("departments", employeesCodeService.findAllDepartment());
        return "user/employees/addForm";
    }

    @GetMapping("/employees/{userId}/detail")
    public String employeesDetail(@PathVariable String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        return "user/employees/detail";
    }

    /*==================  지도위원 ==================*/
    @GetMapping({"/educator", "/educator/{educatorsType}"})
    public String educator(@PathVariable(required = false) Long educatorsType, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        Page<User> educators = userService.findAllEducators(searchParam, educatorsType, pageable);
        model.addAttribute("educators", educators);

        return "user/educator/educators";
    }

    @GetMapping("/educator/add")
    public String addEducator(@ModelAttribute User user, Model model) {
        return "user/educator/addForm";
    }
}
