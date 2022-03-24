package com.samin.dosan.domain.user.entity;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class UserEmployment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_employment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 50, nullable = false)
    private String cmpNm;

    private LocalDate firstDayOfWork;

    private LocalDate lastDayOfWork;

    @Column(length = 20, nullable = false)
    private String position;

    /*================== Business Logic ==================*/
    public void setParent(User user) {
        this.user = user;
    }
}
