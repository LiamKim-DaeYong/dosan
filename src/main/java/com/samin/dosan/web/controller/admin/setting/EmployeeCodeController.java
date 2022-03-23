package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeService;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeType;
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
    public String addView(@ModelAttribute EmployeeCode employeeCode) {
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
        employeeCodeService.save(EmployeeCode.of("임원", EmployeeCodeType.TYPE));
        employeeCodeService.save(EmployeeCode.of("직원", EmployeeCodeType.TYPE));
        employeeCodeService.save(EmployeeCode.of("원장", EmployeeCodeType.POSITION));
        employeeCodeService.save(EmployeeCode.of("4급", EmployeeCodeType.RANK));
        employeeCodeService.save(EmployeeCode.of("4호봉", EmployeeCodeType.STEP));
        employeeCodeService.save(EmployeeCode.of("수련기획실", EmployeeCodeType.DEPARTMENT));
    }
}
