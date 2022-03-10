package com.samin.dosan.domain.user;

import com.samin.dosan.core.code.Address;
import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.former.Former;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(length = 100, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String userNm;

    @Column(length = 10, nullable = false)
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "former_id")
    private Former former;

    @Column(length = 14)
    private String phoneNum;

    @Column(length = 14)
    private String officeNum;

    @Column(length = 14)
    private String homeNum;

    @Column(length = 8)
    private LocalDate dateOfBirth;

    @Column(length = 200)
    private String email;

    @Column(length = 100)
    private String bank;

    @Column(length = 100)
    private String accountNum;

    @Embedded
    private Address address;

    /* 지도위원 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_type")
    private EducatorCode educatorType;

    private LocalDate appointmentDate;

    private LocalDate retirementDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_branch")
    private EducatorCode educatorBranch;

    /* 임직원 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_type")
    private EmployeesCode employeesType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_position")
    private EmployeesCode employeesPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_rank")
    private EmployeesCode employeesRank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_step")
    private EmployeesCode employeesStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_department")
    private EmployeesCode employeesDepartment;
}
