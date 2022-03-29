package com.samin.dosan.domain.training.program.school;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class SchoolProgramEducator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "school_program_educator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_program_id")
    private SchoolProgram schoolProgram;
}
