package com.samin.dosan.domain.homepage.report.file;

import com.samin.dosan.domain.homepage.report.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_report_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ReportFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;

    @Column(nullable = false)
    private Long fileId;

    @Column(nullable = false)
    private LocalDate regDt;

    public void setParent(Report report) {
        this.report = report;
    }
}
