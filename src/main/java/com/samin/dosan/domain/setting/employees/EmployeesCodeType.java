package com.samin.dosan.domain.setting.employees;

import lombok.Getter;

@Getter
public enum EmployeesCodeType {
    TYPE("구분"), POSITION("직위"), RANK("직급"), STEP("호봉"), DEPARTMENT("근무부서");

    private final String description;

    EmployeesCodeType(String description) {
        this.description = description;
    }
}
