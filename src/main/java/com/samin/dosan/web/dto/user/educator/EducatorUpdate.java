package com.samin.dosan.web.dto.user.educator;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.user.educator.entity.Educator;
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
public class EducatorUpdate {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String userNm;

    private Gender gender;

    private Long formerJobCodeId;

    private String phoneNum;

    private String officeNum;

    private String homeNum;

    private LocalDate birthDate;

    @Email
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

    List<EducatorEducational> educationalList = new ArrayList<>();

    List<EducatorEmployment> employmentList = new ArrayList<>();

    private String etc;

    /*================== Business Logic ==================*/
    public static EducatorUpdate fromEntity(Educator educator) {
        EducatorUpdate educatorUpdate = new EducatorUpdate();

        educatorUpdate.userId = educator.getUser().getUserId();
        educatorUpdate.userNm = educator.getUser().getUserNm();
        educatorUpdate.gender = educator.getGender();
        educatorUpdate.formerJobCodeId = educator.getFormerJobCode().getId();
        educatorUpdate.phoneNum = educator.getPhoneNum();
        educatorUpdate.officeNum = educator.getOfficeNum();
        educatorUpdate.homeNum = educator.getHomeNum();
        educatorUpdate.birthDate = educator.getBirthDate();
        educatorUpdate.email = educator.getEmail();
        educatorUpdate.bank = educator.getBank();
        educatorUpdate.account = educator.getAccount();
        educatorUpdate.zipCode = educator.getAddress().getZipCode();
        educatorUpdate.roadAddress = educator.getAddress().getRoadAddress();
        educatorUpdate.detailAddress = educator.getAddress().getDetailAddress();

        educatorUpdate.educatorTypeId = educator.getEducatorType().getId();
        educatorUpdate.educatorAssignedTaskId = educator.getEducatorAssignedTask().getId();
        educatorUpdate.educatorTeamId = educator.getEducatorTeam().getId();
        educatorUpdate.educatorBranchId = educator.getEducatorBranch().getId();
        educatorUpdate.firstDayOfWork = educator.getFirstDayOfWork();

        educatorUpdate.cmpZipCode = educator.getCmpAddress().getZipCode();
        educatorUpdate.cmpRoadAddress = educator.getCmpAddress().getRoadAddress();
        educatorUpdate.cmpDetailAddress = educator.getCmpAddress().getDetailAddress();

        educatorUpdate.etc = educator.getEtc();

        educatorUpdate.educationalList = educator.getEducationalList();

        if (educatorUpdate.educationalList.size() == 0) {
            educatorUpdate.educationalList.add(new EducatorEducational());
        }

        educatorUpdate.employmentList = educator.getEmploymentList();

        if (educatorUpdate.employmentList.size() == 0) {
            educatorUpdate.employmentList.add(new EducatorEmployment());
        }

        return educatorUpdate;
    }
}
