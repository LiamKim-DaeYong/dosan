package com.samin.dosan.web.dto.user.employee;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.user.employees.entity.Employee;
import com.samin.dosan.domain.user.employees.entity.EmployeeEducational;
import com.samin.dosan.domain.user.employees.entity.EmployeeEmployment;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeUpdate {

    private String userId;

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

        employeeUpdate.educationalList = employee.getEducationalList();

        if (employeeUpdate.educationalList.size() == 0) {
            employeeUpdate.educationalList.add(new EmployeeEducational());
        }

        employeeUpdate.employmentList = employee.getEmploymentList();

        if (employeeUpdate.employmentList.size() == 0) {
            employeeUpdate.employmentList.add(new EmployeeEmployment());
        }

        return employeeUpdate;
    }
}
