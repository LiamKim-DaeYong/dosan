package com.samin.dosan.web.dto.user;

import com.samin.dosan.core.code.Address;
import com.samin.dosan.core.code.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmployeeSave {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String userNm;

    private Gender gender;

    private Long formerId;

    private String phoneNum;

    private String officeNum;

    private String homeNum;

    private String birth;

    @Email
    private String email;

    private String bank;

    private String accountNum;

    private Address address;
}
