package com.samin.dosan.domain.homepage.training_review;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.SecretType;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "homepage_training_review")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainingReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private int hit;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private SecretType secretType;

    @Column
    private String password;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public void delete() {
        this.used = Used.N;
    }
}
