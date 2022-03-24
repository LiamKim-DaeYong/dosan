package com.samin.dosan.web.dto.user;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.user.entity.UserEducational;
import com.samin.dosan.domain.user.entity.UserEmployment;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeSave {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String userNm;

    private Gender gender;

    private Long formerJobCodeId;

    private String phoneNum;

    private String officeNum;

    private String homeNum;

    private LocalDate birthDate;

    @Email
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

    private List<UserEducational> educationalList = new ArrayList<>();

    private List<UserEmployment> employmentList = new ArrayList<>();

    private String etc;

    /*================== Business Logic ==================*/
    protected EmployeeSave() {
        educationalList.add(new UserEducational());
        employmentList.add(new UserEmployment());
    }
}
