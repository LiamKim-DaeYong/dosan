package com.samin.dosan.domain.setting.employees;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeesCode extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "emplyees_code_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    private EmployeesCodeType employeesCodeType;

    @Enumerated(EnumType.STRING)
    private Used used;

    /*================== Business Logic ==================*/
    public EmployeesCode init(String type) {
        this.employeesCodeType = EmployeesCodeType.valueOf(type.toUpperCase());
        this.used = Used.Y;

        return this;
    }

    public void update(EmployeesCode updateData) {
        this.code = updateData.getCode();
    }

    public void delete() {
        this.used = Used.N;
    }
}
