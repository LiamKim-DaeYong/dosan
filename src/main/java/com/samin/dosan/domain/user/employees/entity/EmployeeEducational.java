package com.samin.dosan.domain.user.employees.entity;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class EmployeeEducational extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_educational_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(length = 100, nullable = false)
    private String schoolNm;

    @Column(length = 4, nullable = false)
    private String graduationYear;

    @Column(length = 50)
    private String major;

    @Column(length = 10)
    private String degree;

    //================== 연관 관계 메서드 ==================//
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    //==================   생성 메서드   ==================//

    //==================  비즈니스 로직  ==================//
    public void update(EmployeeEducational updateData) {
        this.schoolNm = updateData.getSchoolNm();
        this.graduationYear = updateData.getGraduationYear();
        this.major = updateData.getMajor();
        this.degree = updateData.getDegree();
    }

    //==================   조회 메서드   ==================//

}
