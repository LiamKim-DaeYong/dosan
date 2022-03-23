package com.samin.dosan.domain.user.employees;

import com.samin.dosan.core.code.Address;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserInfo;
import com.samin.dosan.web.dto.user.EmployeeSave;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    /*================== Business Logic ==================*/
    public static Employee of(EmployeeSave saveData) {
        User user = User.of(saveData.getUserId(), saveData.getPassword(), saveData.getUserNm(), Role.ROLE_EMPLOYEE);
        FormerJobCode formerJobCode = FormerJobCode.of(saveData.getFormerJobCodeId());

        Employee employee = new Employee();
        employee.user = user;
        employee.gender = saveData.getGender();
        employee.formerJobCode = formerJobCode;
        employee.phoneNum = saveData.getPhoneNum();
        employee.officeNum = saveData.getOfficeNum();
        employee.homeNum = saveData.getHomeNum();
        employee.birthDate = saveData.getBirthDate();
        employee.email = saveData.getEmail();
        employee.bank = saveData.getBank();
        employee.account = saveData.getAccount();
        employee.address = new Address(saveData.getZipCode(), saveData.getRoadAddress(), saveData.getDetailAddress());
        employee.etc = saveData.getEtc();
        employee.cmpAddress = new Address(saveData.getCmpZipCode(), saveData.getCmpRoadAddress(), saveData.getCmpDetailAddress());

        return employee;
    }
}
