package com.samin.dosan.domain.training_archive.operational;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.dto.training_archive.OperationalSave;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "operational_archive")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Operational extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationalType operationalType;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Operational of(OperationalSave saveData) {
        Operational operational = new Operational();
        operational.used = Used.Y;

        operational.operationalType = OperationalType.valueOf(StrUtils.urlToEnumName(saveData.getOperationalType()));
        operational.user = saveData.getUser();
        operational.title = saveData.getTitle();
        operational.content = saveData.getContent();

        return operational;
    }
}
