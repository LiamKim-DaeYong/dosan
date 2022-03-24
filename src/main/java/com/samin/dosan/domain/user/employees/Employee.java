package com.samin.dosan.domain.user.employees;

import com.samin.dosan.core.domain.Address;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.entity.UserEducational;
import com.samin.dosan.domain.user.entity.UserEmployment;
import com.samin.dosan.domain.user.entity.UserInfo;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "employees")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user = new User();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type")
    private EmployeeCode employeeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_position")
    private EmployeeCode employeePosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_rank")
    private EmployeeCode employeeRank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_step")
    private EmployeeCode employeeStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_department")
    private EmployeeCode employeeDepartment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserEducational> educationalList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserEmployment> employmentList = new ArrayList<>();

    /*================== Business Logic ==================*/
    public static Employee of(EmployeeSave saveData) {
        User user = User.of(saveData.getUserId(), saveData.getPassword(), saveData.getUserNm(), Role.ROLE_EMPLOYEE);
        Employee employee = new Employee();

        employee.user = user;
        employee.gender = saveData.getGender();
        employee.formerJobCode = FormerJobCode.of(saveData.getFormerJobCodeId());
        employee.phoneNum = saveData.getPhoneNum();
        employee.officeNum = saveData.getOfficeNum();
        employee.homeNum = saveData.getHomeNum();
        employee.birthDate = saveData.getBirthDate();
        employee.email = saveData.getEmail();
        employee.bank = saveData.getBank();
        employee.account = saveData.getAccount();
        employee.address = new Address(saveData.getZipCode(), saveData.getRoadAddress(), saveData.getDetailAddress());

        employee.employeeType = EmployeeCode.of(saveData.getEmployeeTypeId());
        employee.employeePosition = EmployeeCode.of(saveData.getEmployeePositionId());
        employee.employeeRank = EmployeeCode.of(saveData.getEmployeeRankId());
        employee.employeeStep = EmployeeCode.of(saveData.getEmployeeStepId());
        employee.employeeDepartment = EmployeeCode.of(saveData.getEmployeeDepartmentId());
        employee.cmpAddress = new Address(saveData.getCmpZipCode(), saveData.getCmpRoadAddress(), saveData.getCmpDetailAddress());

        employee.etc = saveData.getEtc();

        return employee;
    }
}
