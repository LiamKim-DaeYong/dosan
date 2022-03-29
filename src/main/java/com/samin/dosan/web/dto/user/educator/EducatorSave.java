package com.samin.dosan.web.dto.user.educator;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.user.educator.entity.EducatorEducational;
import com.samin.dosan.domain.user.educator.entity.EducatorEmployment;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class EducatorSave {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

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

    private Long educatorTypeId;

    private Long educatorAssignedTaskId;

    private Long educatorTeamId;

    private Long educatorBranchId;

    private LocalDate firstDayOfWork;

    private String cmpZipCode;

    private String cmpRoadAddress;

    private String cmpDetailAddress;

    private String etc;

    List<EducatorEducational> educationalList = new ArrayList<>();

    List<EducatorEmployment> employmentList = new ArrayList<>();

    public EducatorSave() {
        educationalList.add(new EducatorEducational());
        employmentList.add(new EducatorEmployment());
    }
}
