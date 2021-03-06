package com.samin.dosan.web.dto.user.employee;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.user.employees.entity.EmployeeEducational;
import com.samin.dosan.domain.user.employees.entity.EmployeeEmployment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class EmployeeSave {

    @NotBlank
    private String userId;

    private String password = "0000";

    @NotBlank
    private String userNm;

    private Gender gender;

    private Long formerJobCodeId;

    @NotBlank
    private String phoneNum;

    private String officeNum;

    private String homeNum;

    private LocalDate birthDate;

    @Email
    @NotBlank
    private String email;

    private String bank;

    private String account;

    private String zipCode;

    private String roadAddress;

    private String detailAddress;

    private Long employeeTypeId;

    private Long employeePositionId;

    private Long employeeRankId;

    private Long employeeStepId;

    private Long employeeDepartmentId;

    private LocalDate firstDayOfWork;

    private String cmpZipCode;

    private String cmpRoadAddress;

    private String cmpDetailAddress;

    private List<EmployeeEducational> educationalList = new ArrayList<>();

    private List<EmployeeEmployment> employmentList = new ArrayList<>();

    private String etc;

    /*================== Business Logic ==================*/
    public EmployeeSave() {
        educationalList.add(new EmployeeEducational());
        employmentList.add(new EmployeeEmployment());
    }
}
