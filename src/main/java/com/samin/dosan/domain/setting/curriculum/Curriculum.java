package com.samin.dosan.domain.setting.curriculum;

import com.samin.dosan.domain.BaseEntity;
import com.samin.dosan.domain.type.CurriculumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curriculum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, nullable = false)
    private String subject;

    @Column(length = 100,nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private CurriculumType curriculumType;
}
