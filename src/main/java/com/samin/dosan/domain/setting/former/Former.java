package com.samin.dosan.domain.setting.former;

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
public class Former extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "former_id")
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String formerName;

    @Enumerated(EnumType.STRING)
    private Used used;

    /*================== Business Logic ==================*/
    public Former init() {
        this.used = Used.Y;

        return this;
    }

    public void update(Former updateData) {
        this.formerName = updateData.getFormerName();
    }

    public void delete() {
        this.used = Used.N;
    }
}
