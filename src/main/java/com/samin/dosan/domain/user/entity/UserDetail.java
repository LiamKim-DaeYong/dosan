package com.samin.dosan.domain.user.entity;

import com.samin.dosan.core.code.Address;
import com.samin.dosan.core.code.Gender;
import com.samin.dosan.domain.setting.former.Former;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail extends User {

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "former_id")
    private Former former;

    @Column(length = 14)
    private String phoneNum;

    @Column(length = 14)
    private String officeNum;

    @Column(length = 14)
    private String homeNum;

    @Column(length = 8)
    private LocalDate dateOfBirth;

    @Column(length = 200)
    private String email;

    @Column(length = 100)
    private String bank;

    @Column(length = 100)
    private String accountNum;

    @Embedded
    private Address address;
}
