package com.samin.dosan.domain.homepage.popup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "homepage_popup")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Popup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long fileId;

    @Column(length = 1, nullable = false)
    private String postYn;

    @Column(length = 1, nullable = false)
    private String dateSet;

    @Column(length = 20, nullable = false)
    private String postStart;

    @Column(length = 20, nullable = false)
    private String postEnd;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 255)
    private String link;

    @Column(nullable = false)
    private LocalDateTime regDt;
}
