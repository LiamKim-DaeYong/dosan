package com.samin.dosan.domain.training.program.school;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
import com.samin.dosan.domain.training.program.file.ProgramFile;
import com.samin.dosan.domain.user.educator.entity.Educator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchoolProgram extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "school_program_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_inquiry_record_id")
    private TrainingInquiryRecords trainingInquiryRecords;

    private String manager;

    private Integer totalClass;

    private Integer totalStudent;

    @Column(columnDefinition = "TEXT")
    private String leaders;

    @OneToMany(mappedBy = "schoolProgram", cascade = CascadeType.ALL)
    private List<SchoolProgramEducator> educators = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_table_file_id")
    private ProgramFile assignmentTable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_file_id")
    private ProgramFile program;

    private LocalDate transitDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transit_educator")
    private Educator transitEducator;

    private LocalDate withdrawDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "withdraw_educator")
    private Educator withdrawEducator;

    @Column(columnDefinition = "TEXT")
    private String etc;

    /*================== Business Logic ==================*/
}
