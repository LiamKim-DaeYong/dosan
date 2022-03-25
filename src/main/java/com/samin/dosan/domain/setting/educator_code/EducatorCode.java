package com.samin.dosan.domain.setting.educator_code;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducatorCode extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "educator_code_id")
    private Long id;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EducatorCodeType educatorCodeType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static EducatorCode of(Long educatorCodeId) {
        EducatorCode educatorCode = new EducatorCode();
        educatorCode.id = educatorCodeId;

        return educatorCode;
    }

    public static EducatorCode of(EducatorCode saveData, String type) {
        EducatorCode educatorCode = new EducatorCode();
        educatorCode.code = saveData.getCode();
        educatorCode.educatorCodeType = EducatorCodeType.valueOf(StrUtils.urlToEnumName(type));
        educatorCode.used = Used.Y;

        return educatorCode;
    }

    public static EducatorCode of(String code, EducatorCodeType educatorCodeType) {
        EducatorCode educatorCode = new EducatorCode();
        educatorCode.code = code;
        educatorCode.educatorCodeType = educatorCodeType;
        educatorCode.used = Used.Y;

        return educatorCode;
    }

    @Builder(builderMethodName = "test")
    public EducatorCode(Long id, String code, EducatorCodeType educatorCodeType, Used used) {
        this.id = id;
        this.code = code;
        this.educatorCodeType = educatorCodeType;
        this.used = used;
    }

    public void update(EducatorCode updateData) {
        this.code = updateData.getCode();
    }

    public void delete() {
        this.used = Used.N;
    }
}
