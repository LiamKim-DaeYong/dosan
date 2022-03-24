package com.samin.dosan.web.dto.user;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.user.employees.Employee;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class EmployeeUpdate {

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

    private String etc;

    /*================== Business Logic ==================*/
    public static EmployeeUpdate fromEntity(Employee employee) {
        EmployeeUpdate employeeUpdate = new EmployeeUpdate();

        employeeUpdate.userId = employee.getUser().getUserId();
        employeeUpdate.userNm = employee.getUser().getUserNm();
        employeeUpdate.gender = employee.getGender();
        employeeUpdate.formerJobCodeId = employee.getFormerJobCode().getId();
        employeeUpdate.phoneNum = employee.getPhoneNum();
        employeeUpdate.officeNum = employee.getOfficeNum();
        employeeUpdate.homeNum = employee.getHomeNum();
        employeeUpdate.birthDate = employee.getBirthDate();
        employeeUpdate.email = employee.getEmail();
        employeeUpdate.bank = employee.getBank();
        employeeUpdate.account = employee.getAccount();
        employeeUpdate.zipCode = employee.getAddress().getZipCode();
        employeeUpdate.roadAddress = employee.getAddress().getRoadAddress();
        employeeUpdate.detailAddress = employee.getAddress().getDetailAddress();

        employeeUpdate.employeeTypeId = employee.getEmployeeType().getId();
        employeeUpdate.employeePositionId = employee.getEmployeePosition().getId();
        employeeUpdate.employeeRankId = employee.getEmployeeRank().getId();
        employeeUpdate.employeeStepId = employee.getEmployeeStep().getId();
        employeeUpdate.employeeDepartmentId = employee.getEmployeeDepartment().getId();
        employeeUpdate.firstDayOfWork = employee.getFirstDayOfWork();

        employeeUpdate.cmpZipCode = employee.getCmpAddress().getZipCode();
        employeeUpdate.cmpRoadAddress = employee.getCmpAddress().getRoadAddress();
        employeeUpdate.cmpDetailAddress = employee.getCmpAddress().getDetailAddress();

        employeeUpdate.etc = employee.getEtc();

        return employeeUpdate;
    }
}
