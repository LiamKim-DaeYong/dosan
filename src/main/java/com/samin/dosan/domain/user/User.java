package com.samin.dosan.domain.user;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(length = 100, nullable = false)
    private String userId; //아이디

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(length = 100, nullable = false)
    private String userNm; //이름

    @Column(length = 10)
    private Gender gender; //성별

    @Column
    private Long formerId;

    @Column(length = 15)
    private String phoneNum; //h.p

    @Column(length = 15)
    private String officeNum; //사무실

    @Column(length = 15)
    private String homeNum; //집

    @Column(length = 15)
    private String birth; //생년월일

    @Column(length = 100)
    private String email; //이메일

    @Column(length = 20)
    private String bank; //은행

    @Column(length = 50)
    private String accountNum; //계좌번호

    @Column(length = 10)
    private String area; //지역

    @Column(length = 20)
    private String role; //권한정보

    @Enumerated(EnumType.STRING)
    private UserType userType; //관리자, 임직원, 지도위원 type

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used; //사용여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type")
    private EmployeesCode employeesType;

    /*================== Business Logic ==================*/

    public User newAdmin(UserType userType) {
        this.role = "ROLE_ADMIN";
        this.userType = userType;
        this.used = Used.Y;
        return this;
    }

    public User newEmployee(UserType userType, String password) {
        this.password = password;
        this.role = "ROLE_MANAGER";
        this.userType = userType;
        this.used = Used.Y;
        return this;
    }

    public User newEducator(UserType userType) {
        this.role = "ROLE_USER";
        this.userType = userType;
        this.used = Used.Y;
        return this;
    }

    public User edit(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userNm = user.getUserNm();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.userType = user.getUserType();
        this.used = user.getUsed();

        return this;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
