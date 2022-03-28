package com.samin.dosan.domain.user.employees.entity;

import com.samin.dosan.core.domain.Address;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.entity.UserInfo;
import com.samin.dosan.web.dto.user.employee.EmployeeSave;
import com.samin.dosan.web.dto.user.employee.EmployeeUpdate;
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

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeEducational> educationalList = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeEmployment> employmentList = new ArrayList<>();

    /*================== Business Logic ==================*/
    public static Employee of(EmployeeSave saveData, User user) {
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

        saveData.getEducationalList().stream().forEach(educational -> { educational.setEmployee(employee); });
        employee.educationalList = saveData.getEducationalList();

        saveData.getEmploymentList().stream().forEach(employment -> { employment.setEmployee(employee); });
        employee.employmentList = saveData.getEmploymentList();

        return employee;
    }

    public void update(EmployeeUpdate updateData) {
        this.gender = updateData.getGender();
        this.formerJobCode = FormerJobCode.of(updateData.getFormerJobCodeId());

        this.phoneNum = updateData.getPhoneNum();
        this.officeNum = updateData.getOfficeNum();
        this.homeNum = updateData.getHomeNum();
        this.birthDate = updateData.getBirthDate();
        this.email = updateData.getEmail();
        this.bank = updateData.getBank();
        this.account = updateData.getAccount();
        this.address = new Address(updateData.getZipCode(), updateData.getRoadAddress(), updateData.getDetailAddress());

        this.employeeType = EmployeeCode.of(updateData.getEmployeeTypeId());
        this.employeePosition = EmployeeCode.of(updateData.getEmployeePositionId());
        this.employeeRank = EmployeeCode.of(updateData.getEmployeeRankId());
        this.employeeStep = EmployeeCode.of(updateData.getEmployeeStepId());
        this.employeeDepartment = EmployeeCode.of(updateData.getEmployeeDepartmentId());
        this.cmpAddress = new Address(updateData.getCmpZipCode(), updateData.getCmpRoadAddress(), updateData.getCmpDetailAddress());

        this.etc = updateData.getEtc();

        updateEducationalList(updateData.getEducationalList());
        updateEmploymentList(updateData.getEmploymentList());
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void updateEducationalList(List<EmployeeEducational> updateDataList) {
        List<EmployeeEducational> resultList = new ArrayList<>();

        updateDataList.stream().forEach(updateData -> {
            EmployeeEducational findData = this.educationalList
                    .stream().filter(educational -> educational.getId().equals(updateData.getId()))
                    .findFirst().orElse(new EmployeeEducational());

            findData.setEmployee(this);
            findData.update(updateData);
            resultList.add(findData);
        });

        this.educationalList.clear();
        this.educationalList.addAll(resultList);
    }

    private void updateEmploymentList(List<EmployeeEmployment> updateDataList) {
        List<EmployeeEmployment> resultList = new ArrayList<>();

        updateDataList.stream().forEach(updateData -> {
            EmployeeEmployment findData = this.employmentList
                    .stream().filter(educational -> educational.getId().equals(updateData.getId()))
                    .findFirst().orElse(new EmployeeEmployment());

            findData.setEmployee(this);
            findData.update(updateData);
            resultList.add(findData);
        });

        this.employmentList.clear();
        this.employmentList.addAll(resultList);
    }
}
