package com.samin.dosan.domain.setting.former_code;

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
public class FormerCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "former_code_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String formerNm;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public FormerCode init() {
        this.used = Used.Y;
        return this;
    }

    public void update(FormerCode updateData) {
        this.formerNm = updateData.getFormerNm();
    }

    public void delete() {
        this.used = Used.N;
    }
}
