package com.samin.dosan.domain.setting.employee_code;

import com.samin.dosan.core.code.EnumNameParser;
import lombok.Getter;

@Getter
public enum EmployeeCodeType implements EnumNameParser {
    TYPE("구분"), POSITION("직위"), RANK("직급"), STEP("호봉"), DEPARTMENT("근무부서");

    private final String description;

    EmployeeCodeType(String description) {
        this.description = description;
    }
}
