package com.samin.dosan.domain.user;

import com.samin.dosan.core.code.Address;
import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.former.Former;
import com.samin.dosan.web.dto.user.EmployeeSave;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "former_id")
    private Former former;

    @Column(length = 15)
    private String phoneNum;

    @Column(length = 15)
    private String officeNum;

    @Column(length = 15)
    private String homeNum;

    @Column(length = 15)
    private String birth;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String bank;

    @Column(length = 50)
    private String accountNum;

    @Column(length = 20)
    private String role;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type")
    private EmployeesCode employeesType;

    /*================== Business Logic ==================*/

    public User newAdmin() {
        this.role = "ROLE_ADMIN";
        this.userType = UserType.ADMIN;
        this.used = Used.Y;
        return this;
    }

    public User newEducator() {
        this.role = "ROLE_USER";
        this.userType = UserType.EDUCATOR;
        this.used = Used.Y;
        return this;
    }

    public static User newEmployee(EmployeeSave saveData) {
        return User.builder()
                .userId(saveData.getUserId())
                .password(saveData.getPassword())
                .userNm(saveData.getUserNm())
                .gender(saveData.getGender())
                .former(Former.builder().id(saveData.getFormerId()).build())
                .phoneNum(saveData.getPhoneNum())
                .officeNum(saveData.getOfficeNum())
                .homeNum(saveData.getHomeNum())
                .birth(saveData.getBirth())
                .email(saveData.getEmail())
                .bank(saveData.getBank())
                .accountNum(saveData.getAccountNum())
                .address(saveData.getAddress())
                .role("ROLE_MANAGER")
                .userType(UserType.EMPLOYEES)
                .used(Used.Y)
                .build();
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
