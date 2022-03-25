package com.samin.dosan.domain.user.employees.entity;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class EmployeeEmployment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_employment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(length = 50, nullable = false)
    private String cmpNm;

    private LocalDate firstDayOfWork;

    private LocalDate lastDayOfWork;

    @Column(length = 20, nullable = false)
    private String position;

    /*================== Business Logic ==================*/
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void update(EmployeeEmployment updateData) {
        this.cmpNm = updateData.getCmpNm();
        this.firstDayOfWork = updateData.getFirstDayOfWork();
        this.lastDayOfWork = updateData.getLastDayOfWork();
        this.position = updateData.getPosition();
    }
}
