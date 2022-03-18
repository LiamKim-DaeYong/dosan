package com.samin.dosan.domain.homepage.data.promotion;

import com.samin.dosan.core.code.Used;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_promotion")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String code;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public Promotion init() {
        this.used = Used.Y;
        this.author = "도산선비 문화수련원";
        this.regDt = LocalDate.now();

        return this;
    }

    public void update(Promotion updateData) {
        this.title = updateData.getTitle();
        this.code = updateData.getCode();
    }

    public void delete() {
        this.used = Used.N;
    }
}
