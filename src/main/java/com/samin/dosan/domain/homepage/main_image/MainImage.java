package com.samin.dosan.domain.homepage.main_image;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_main_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 1, nullable = false)
    private String postYn;

    @Column(nullable = false)
    private Integer sort;

    @Column(nullable = false)
    private String originFilename;

    @Column(nullable = false)
    private String storeFileName;

    @Column(nullable = false)
    private LocalDate regDt;
}
