package com.samin.dosan.domain.schedule.etc;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 100)
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String color;

    @Column(length = 255)
    private String bgColor;

    @Column(length = 255)
    private String dragBgColor;

    @Column(length = 255)
    private String borderColor;

}
