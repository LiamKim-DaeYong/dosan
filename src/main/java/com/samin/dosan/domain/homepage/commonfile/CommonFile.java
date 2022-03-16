package com.samin.dosan.domain.homepage.commonfile;

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
@Table(name = "homepage_common_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class CommonFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long fileSize;

    @Column(length = 255, nullable = false)
    private String orgFilename;

    @Column(length = 255, nullable = false)
    private String filename;

    @Column(length = 255, nullable = false)
    private String filePath;

    @Column(length = 255, nullable = false)
    private String extension;

    @Column(nullable = false)
    private LocalDate regDt;
}
