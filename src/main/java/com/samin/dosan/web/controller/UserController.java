package com.samin.dosan.web.controller;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeService;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.employees.EmployeesCodeService;
import com.samin.dosan.domain.setting.former.Former;
import com.samin.dosan.domain.setting.former.FormerService;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
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
    private final EmployeesCodeService employeesCodeService;
    private final EducatorCodeService educatorCodeService;
    private final FormerService formerService;

    /*==================  임직원 ==================*/
    @GetMapping({"/employees", "/employees/{employeesType}"})
    public String employees(@PathVariable(required = false) Long employeesType, @ModelAttribute SearchParam searchParam,
                            Pageable pageable, Model model) {
        Page<User> employees = userService.findAllEmployees(searchParam, employeesType, pageable);
        model.addAttribute("employees", employees);

        List<EmployeesCode> employeesTypes = employeesCodeService.findAllTypes();
        model.addAttribute("employeesTypes", employeesTypes);

        return "user/employees/employees";
    }

    @GetMapping("/employees/add")
    public String addEmployees(@ModelAttribute User user, Model model) {
        List<Former> formers = formerService.findAllList();
        model.addAttribute("formers", formers);

        List<EmployeesCode> employeesTypes = employeesCodeService.findAllTypes();
        model.addAttribute("employeesTypes", employeesTypes);



        return "user/employees/addForm";
    }

    /*==================  지도위원 ==================*/
    @GetMapping({"/educator", "/educator/{educatorType}"})
    public String educator(@PathVariable(required = false) Long educatorType, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        Page<User> educators = userService.findAllEducators(searchParam, educatorType, pageable);
        model.addAttribute("educators", educators);

        List<EducatorCode> educatorTypes = educatorCodeService.findAllTypes();
        model.addAttribute("educatorTypes", educatorTypes);

        return "user/educator/educators";
    }

    @GetMapping("/educator/add")
    public String addEducator(@ModelAttribute User user, Model model) {
        return "user/educator/addForm";
    }
}
