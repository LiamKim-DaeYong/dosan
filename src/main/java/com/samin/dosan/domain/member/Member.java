package com.samin.dosan.domain.member;

import com.samin.dosan.core.code.Address;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(length = 100, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String userNm;

    @Column(length = 10, nullable = false)
    private String gender;

    @Column(length = 20)
    private String former;

    @Column(length = 15, nullable = false)
    private String phoneNum;

    @Column(length = 15)
    private String officeNum;

    @Column(length = 15)
    private String homeNum;

    private LocalDate dateOfBirth;

    private String email;

    @Column(length = 20)
    private String bank;

    @Column(length = 50)
    private String accountNum;

    @Column(length = 10)
    private String area;

    @Embedded
    private Address address;

//    @Column(length = 30)
//    private String workSec;
//
//    private LocalDate appointmentDt;
//
//    private LocalDate retirementDt;
//
//    @Column(length = 10)
//    private String branch;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="zipCode", column = @Column(name = "CMP_ZIP_CODE"))
//            ,@AttributeOverride(name="roadAddress", column = @Column(name = "CMP_ROAD_ADDRESS"))
//            ,@AttributeOverride(name="detailAddress", column = @Column(name = "CMP_DETAIL_ADDRESS"))
//    })
//    private Address companyAddress;

    public String initPassword() {
        this.password = new BCryptPasswordEncoder().encode("1111");
        return this.password;
    }
}
