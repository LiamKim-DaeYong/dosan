package com.samin.dosan.domain.user.educator.entity;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class EducatorEmployment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "educator_employment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_id")
    private Educator educator;

    @Column(length = 50, nullable = false)
    private String cmpNm;

    private LocalDate firstDayOfWork;

    private LocalDate lastDayOfWork;

    @Column(length = 20, nullable = false)
    private String position;

    /*================== Business Logic ==================*/
    public void setEducator(Educator educator) {
        this.educator = educator;
    }

    public void update(EducatorEmployment updateData) {
        this.cmpNm = updateData.getCmpNm();
        this.firstDayOfWork = updateData.getFirstDayOfWork();
        this.lastDayOfWork = updateData.getLastDayOfWork();
        this.position = updateData.getPosition();
    }
}
