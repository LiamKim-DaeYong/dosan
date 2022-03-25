package com.samin.dosan.domain.user.educator.entity;

import com.samin.dosan.core.domain.Address;
import com.samin.dosan.domain.setting.educator_code.EducatorCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.domain.user.entity.UserInfo;
import com.samin.dosan.web.dto.user.educator.EducatorSave;
import com.samin.dosan.web.dto.user.educator.EducatorUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "educators")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Educator extends UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "educator_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user = new User();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_type")
    private EducatorCode educatorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_assigned_task")
    private EducatorCode educatorAssignedTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_temp")
    private EducatorCode educatorTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_branch")
    private EducatorCode educatorBranch;

    @OneToMany(mappedBy = "educator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducatorEducational> educationalList = new ArrayList<>();

    @OneToMany(mappedBy = "educator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducatorEmployment> employmentList = new ArrayList<>();

    /*================== Business Logic ==================*/
    public static Educator of(EducatorSave saveData, User user) {
        Educator educator = new Educator();

        educator.user = user;
        educator.gender = saveData.getGender();
        educator.formerJobCode = FormerJobCode.of(saveData.getFormerJobCodeId());
        educator.phoneNum = saveData.getPhoneNum();
        educator.officeNum = saveData.getOfficeNum();
        educator.homeNum = saveData.getHomeNum();
        educator.birthDate = saveData.getBirthDate();
        educator.email = saveData.getEmail();
        educator.bank = saveData.getBank();
        educator.account = saveData.getAccount();
        educator.address = new Address(saveData.getZipCode(), saveData.getRoadAddress(), saveData.getDetailAddress());

        educator.educatorType = EducatorCode.of(saveData.getEducatorTypeId());
        educator.educatorAssignedTask = EducatorCode.of(saveData.getEducatorAssignedTaskId());
        educator.educatorTeam = EducatorCode.of(saveData.getEducatorTeamId());
        educator.educatorBranch = EducatorCode.of(saveData.getEducatorBranchId());
        educator.cmpAddress = new Address(saveData.getCmpZipCode(), saveData.getCmpRoadAddress(), saveData.getCmpDetailAddress());

        educator.etc = saveData.getEtc();

        saveData.getEducationalList().stream().forEach(educational -> { educational.setEducator(educator); });
        educator.educationalList = saveData.getEducationalList();

        saveData.getEmploymentList().stream().forEach(employment -> { employment.setEducator(educator); });
        educator.employmentList = saveData.getEmploymentList();

        return educator;
    }

    public void update(EducatorUpdate updateData) {
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

        this.educatorType = EducatorCode.of(updateData.getEducatorTypeId());
        this.educatorAssignedTask = EducatorCode.of(updateData.getEducatorAssignedTaskId());
        this.educatorTeam = EducatorCode.of(updateData.getEducatorTeamId());
        this.educatorBranch = EducatorCode.of(updateData.getEducatorBranchId());
        this.cmpAddress = new Address(updateData.getCmpZipCode(), updateData.getCmpRoadAddress(), updateData.getCmpDetailAddress());

        this.etc = updateData.getEtc();

        updateEducationalList(updateData.getEducationalList());
        updateEmploymentList(updateData.getEmploymentList());
    }

    public void updateEducationalList(List<EducatorEducational> updateDataList) {
        List<EducatorEducational> resultList = new ArrayList<>();

        updateDataList.stream().forEach(updateData -> {
            EducatorEducational findData = this.educationalList
                    .stream().filter(educational -> educational.getId().equals(updateData.getId()))
                    .findFirst().orElse(new EducatorEducational());

            findData.setEducator(this);
            findData.update(updateData);
            resultList.add(findData);
        });

        this.educationalList.clear();
        this.educationalList.addAll(resultList);
    }

    public void updateEmploymentList(List<EducatorEmployment> updateDataList) {
        List<EducatorEmployment> resultList = new ArrayList<>();

        updateDataList.stream().forEach(updateData -> {
            EducatorEmployment findData = this.employmentList
                    .stream().filter(educational -> educational.getId().equals(updateData.getId()))
                    .findFirst().orElse(new EducatorEmployment());

            findData.setEducator(this);
            findData.update(updateData);
            resultList.add(findData);
        });

        this.employmentList.clear();
        this.employmentList.addAll(resultList);
    }
}
