package com.samin.dosan.domain.user.entity;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.domain.Address;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@MappedSuperclass
public class UserInfo extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    protected Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "former_job_code_id")
    protected FormerJobCode formerJobCode;

    @Column(length = 15)
    protected String phoneNum;

    @Column(length = 15)
    protected String officeNum;

    @Column(length = 15)
    protected String homeNum;

    protected LocalDate birthDate;

    @Column(length = 100)
    protected String email;

    @Column(length = 20)
    protected String bank;

    @Column(length = 30)
    protected String account;

    @Embedded
    protected Address address = new Address();

    protected LocalDate firstDayOfWork;

    protected LocalDate lastDayOfWork;

    protected LocalDate dayOffFromWork;

    @Column(columnDefinition = "TEXT")
    protected String etc;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "zipCode", column = @Column(name = "cmp_zip_code")),
        @AttributeOverride(name = "roadAddress", column = @Column(name = "cmp_road_address")),
        @AttributeOverride(name = "detailAddress", column = @Column(name = "cmp_detail_address"))
    })
    protected Address cmpAddress = new Address();
}
