package com.samin.dosan.web.controller.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.employees.EmployeesCodeService;
import com.samin.dosan.domain.setting.employees.EmployeesCodeType;
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

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/employees/{type}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EmployeesCodeController {

    private final EmployeesCodeService employeesCodeService;

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        EmployeesCodeType employeesCodeType = EmployeesCodeType.valueOf(type.toUpperCase());

        Page<EmployeesCode> result = employeesCodeService.findAll(searchParam, employeesCodeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("employeesCodeType", employeesCodeType);
        model.addAttribute("employeesCodeTypes", EmployeesCodeType.values());

        return "setting/employees/employees";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute EmployeesCode saveData) {
        return "setting/employees/addForm::#form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        EmployeesCode employeesCode = employeesCodeService.findById(id);
        model.addAttribute("employeesCode", employeesCode);

        return "setting/employees/editForm::#form";
    }

    @PostConstruct
    public void init() {
        EmployeesCode employeesCode1 = EmployeesCode.builder()
                .code("임원")
                .employeesCodeType(EmployeesCodeType.TYPE)
                .used(Used.Y)
                .build();

        employeesCodeService.save(employeesCode1);

        EmployeesCode employeesCode2 = EmployeesCode.builder()
                .code("직원")
                .employeesCodeType(EmployeesCodeType.TYPE)
                .used(Used.Y)
                .build();

        employeesCodeService.save(employeesCode2);

        EmployeesCode employeesCode3 = EmployeesCode.builder()
                .code("원장")
                .employeesCodeType(EmployeesCodeType.POSITION)
                .used(Used.Y)
                .build();

        employeesCodeService.save(employeesCode3);

        EmployeesCode employeesCode4 = EmployeesCode.builder()
                .code("4급")
                .employeesCodeType(EmployeesCodeType.RANK)
                .used(Used.Y)
                .build();

        employeesCodeService.save(employeesCode4);

        EmployeesCode employeesCode5 = EmployeesCode.builder()
                .code("4호봉")
                .employeesCodeType(EmployeesCodeType.STEP)
                .used(Used.Y)
                .build();

        employeesCodeService.save(employeesCode5);

        EmployeesCode employeesCode6 = EmployeesCode.builder()
                .code("수련기획실")
                .employeesCodeType(EmployeesCodeType.DEPARTMENT)
                .used(Used.Y)
                .build();

        employeesCodeService.save(employeesCode6);
    }
}
