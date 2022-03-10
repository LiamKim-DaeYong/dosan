package com.samin.dosan.domain.setting.educator;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducatorCode extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "educator_code_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private EducatorCodeType educatorCodeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public EducatorCode init(String type) {
        this.educatorCodeType = EducatorCodeType.valueOf(type.toUpperCase());
        this.used = Used.Y;

        return this;
    }

    public void update(EducatorCode updateData) {
        this.code = updateData.getCode();
    }

    public void delete() {
        this.used = Used.N;
    }
}
