package com.samin.dosan.domain.homepage.newsletter;

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
@Table(name = "homepage_newsletter")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Newsletter {

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

    @Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate regDt;
}
