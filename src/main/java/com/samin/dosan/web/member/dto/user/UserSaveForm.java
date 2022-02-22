package com.samin.dosan.web.member.dto.user;

import com.samin.dosan.domain.type.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class UserSaveForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String userNm;

    private String gender;

    private String former;

    private String phoneNum;

    private String officeNum;

    private String homeNum;

    private LocalDate dateOfBirth;

    @Email
    private String email;

    private String bank;

    private String accountNum;

    private String area;

    private Address address;

    private String workSec;

    private LocalDate appointmentDt;

    private LocalDate retirementDt;

    private String branch;

    private Address cmpAddress;
}
