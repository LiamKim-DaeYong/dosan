package com.samin.dosan.domain.homepage.qna;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.homepage.type.SecretType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "homepage_inquiry")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer hit;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private SecretType secret;

    @Column
    private String password;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public void comment(String comment) {
        this.comment = comment;
    }

    public void commentDelete() {
        this.comment = null;
    }

    public void delete() {
        this.used = Used.N;
    }
}
