package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeService;
import com.samin.dosan.domain.setting.employee.EmployeeCodeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/admin/setting/employee-code/{type}")
public class EmployeeCodeController {

    private final EmployeeCodeService employeeCodeService;

    @ModelAttribute("employeeCodeTypes")
    public EmployeeCodeType[] employeeCodeTypes() {
        return EmployeeCodeType.values();
    }

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        EmployeeCodeType employeeCodeType = EmployeeCodeType.valueOf(StrUtils.urlToEnumName(type));

        Page<EmployeeCode> result = employeeCodeService.findAll(searchParam, employeeCodeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("employeeCodeType", employeeCodeType);

        return "admin/setting/employee_code/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute EmployeeCode saveData) {
        return "admin/setting/employee_code/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        EmployeeCode employeeCode = employeeCodeService.findById(id);
        model.addAttribute("employeeCode", employeeCode);

        return "admin/setting/employee_code/editView::#form";
    }

    @PostConstruct
    public void init() {
        EmployeeCode employeeCode1 = EmployeeCode.builder()
                .code("임원")
                .employeeCodeType(EmployeeCodeType.TYPE)
                .used(Used.Y)
                .build();

        employeeCodeService.save(employeeCode1);

        EmployeeCode employeeCode2 = EmployeeCode.builder()
                .code("직원")
                .employeeCodeType(EmployeeCodeType.TYPE)
                .used(Used.Y)
                .build();

        employeeCodeService.save(employeeCode2);

        EmployeeCode employeeCode3 = EmployeeCode.builder()
                .code("원장")
                .employeeCodeType(EmployeeCodeType.POSITION)
                .used(Used.Y)
                .build();

        employeeCodeService.save(employeeCode3);

        EmployeeCode employeeCode4 = EmployeeCode.builder()
                .code("4급")
                .employeeCodeType(EmployeeCodeType.RANK)
                .used(Used.Y)
                .build();

        employeeCodeService.save(employeeCode4);

        EmployeeCode employeeCode5 = EmployeeCode.builder()
                .code("4호봉")
                .employeeCodeType(EmployeeCodeType.STEP)
                .used(Used.Y)
                .build();

        employeeCodeService.save(employeeCode5);

        EmployeeCode employeeCode6 = EmployeeCode.builder()
                .code("수련기획실")
                .employeeCodeType(EmployeeCodeType.DEPARTMENT)
                .used(Used.Y)
                .build();

        employeeCodeService.save(employeeCode6);
    }
}
