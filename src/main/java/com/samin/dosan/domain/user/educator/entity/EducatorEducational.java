package com.samin.dosan.domain.user.educator.entity;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class EducatorEducational extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "educator_educational_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "educator_id")
    private Educator educator;

    @Column(length = 100, nullable = false)
    private String schoolNm;

    @Column(length = 4, nullable = false)
    private String graduationYear;

    @Column(length = 50)
    private String major;

    @Column(length = 10)
    private String degree;

    /*================== Business Logic ==================*/
    public void setEducator(Educator educator) {
        this.educator = educator;
    }

    public void update(EducatorEducational updateData) {
        this.schoolNm = updateData.getSchoolNm();
        this.graduationYear = updateData.getGraduationYear();
        this.major = updateData.getMajor();
        this.degree = updateData.getDegree();
    }
}
