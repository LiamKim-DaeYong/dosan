package com.samin.dosan.domain.setting.subject_code;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_code_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String subject;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private SubjectCodeType subjectCodeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static SubjectCode of(SubjectCode saveData, String type) {
        SubjectCode subjectCode = new SubjectCode();
        subjectCode.subject = saveData.getSubject();
        subjectCode.content = saveData.getSubject();
        subjectCode.subjectCodeType = SubjectCodeType.valueOf(StrUtils.urlToEnumName(type));
        subjectCode.used = Used.Y;

        return subjectCode;
    }

    public void update(SubjectCode updateData) {
        this.subject = updateData.getSubject();
        this.content = updateData.getContent();
    }

    public void delete() {
        this.used = Used.N;
    }
}
