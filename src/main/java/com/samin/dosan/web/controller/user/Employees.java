package com.samin.dosan.web.controller.user;

import com.samin.dosan.core.code.Address;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Employees {

    private String userId;

    private String password;

    private String userNm;

    private String gender;

    private Long former;

    private String phoneNum;

    private String officeNum;

    private String homeNum;

    private LocalDate dateOfBirth;

    private String email;

    private String bank;

    private String accountNum;

    private Address address;

    private Long employeesType;

    private Long employeesPosition;

    private Long employeesRank;

    private Long employeesStep;

    private Long employeesDepartment;
}
