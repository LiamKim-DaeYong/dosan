package com.samin.dosan.domain.setting.former_job_code;

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
public class FormerJobCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "former_job_code_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String formerNm;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static FormerJobCode of(FormerJobCode saveData) {
        FormerJobCode formerJobCode = new FormerJobCode();
        formerJobCode.formerNm = saveData.getFormerNm();
        formerJobCode.used = Used.Y;

        return formerJobCode;
    }

    public void update(FormerJobCode updateData) {
        this.formerNm = updateData.getFormerNm();
    }

    public void delete() {
        this.used = Used.N;
    }
}
