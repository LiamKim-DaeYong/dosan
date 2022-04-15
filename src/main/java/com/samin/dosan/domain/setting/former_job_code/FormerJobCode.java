package com.samin.dosan.domain.setting.former_job_code;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FormerJobCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "former_job_code_id")
    private Long id;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String formerNm;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    //================== 연관 관계 메서드 ==================//

    //==================   생성 메서드   ==================//
    public static FormerJobCode of(Long formerJobCodeId) {
        FormerJobCode formerJobCode = new FormerJobCode();
        formerJobCode.id = formerJobCodeId;

        return formerJobCode;
    }

    public static FormerJobCode of(FormerJobCode saveData) {
        FormerJobCode formerJobCode = new FormerJobCode();
        formerJobCode.formerNm = saveData.getFormerNm();
        formerJobCode.used = Used.Y;

        return formerJobCode;
    }

    /* @PostConstruct 사용(삭제 예정) */
    public static FormerJobCode of(String formerNm) {
        FormerJobCode formerJobCode = new FormerJobCode();
        formerJobCode.formerNm = formerNm;
        formerJobCode.used = Used.Y;

        return formerJobCode;
    }

    @Builder(builderMethodName = "test")
    public FormerJobCode(Long id, String formerNm, Used used) {
        this.id = id;
        this.formerNm = formerNm;
        this.used = used;
    }

    //==================  비즈니스 로직  ==================//
    public void update(FormerJobCode updateData) {
        this.formerNm = updateData.getFormerNm();
    }

    public void delete() {
        this.used = Used.N;
    }

    //==================   조회 메서드   ==================//

}
