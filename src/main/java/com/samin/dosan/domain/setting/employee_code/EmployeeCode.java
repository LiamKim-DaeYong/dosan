package com.samin.dosan.domain.setting.employee_code;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_code_id")
    private Long id;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EmployeeCodeType employeeCodeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    //================== 연관 관계 메서드 ==================//

    //==================   생성 메서드   ==================//
    public static EmployeeCode of(Long employeeCodeId) {
        EmployeeCode employeeCode = new EmployeeCode();
        employeeCode.id = employeeCodeId;

        return employeeCode;
    }

    public static EmployeeCode of(EmployeeCode saveData, String type) {
        EmployeeCode employeeCode = new EmployeeCode();
        employeeCode.code = saveData.getCode();
        employeeCode.employeeCodeType = EmployeeCodeType.valueOf(StrUtils.urlToEnumName(type));
        employeeCode.used = Used.Y;

        return employeeCode;
    }

    public static EmployeeCode of(String code, EmployeeCodeType employeeCodeType) {
        EmployeeCode employeeCode = new EmployeeCode();
        employeeCode.code = code;
        employeeCode.employeeCodeType = employeeCodeType;
        employeeCode.used = Used.Y;

        return employeeCode;
    }

    @Builder(builderMethodName = "test")
    public EmployeeCode(Long id, String code, EmployeeCodeType employeeCodeType, Used used) {
        this.id = id;
        this.code = code;
        this.employeeCodeType = employeeCodeType;
        this.used = used;
    }

    //==================  비즈니스 로직  ==================//
    public void update(EmployeeCode updateData) {
        this.code = updateData.getCode();
    }

    public void delete() {
        this.used = Used.N;
    }

    //==================   조회 메서드   ==================//

}
