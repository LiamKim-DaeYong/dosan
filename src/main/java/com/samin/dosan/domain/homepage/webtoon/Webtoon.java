package com.samin.dosan.domain.homepage.webtoon;

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
@Table(name = "homepage_webtoon")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long pdfId;

    @Column(nullable = false)
    private Long previewId;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate regDt;
}
