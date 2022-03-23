package com.samin.dosan.domain.homepage.newsletter;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_newsletter")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Newsletter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long fileId;

    @Column(nullable = false)
    private Long previewId;

    @Column(nullable = false)
    private int hit;

    @Column(length = 1, nullable = false)
    private String postYn;

    @Column(nullable = false)
    private Long postSeq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate regDt;
}
