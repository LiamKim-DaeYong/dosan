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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "bgColor")
    private String bgColor;

    @Column(name = "dragBgColor")
    private String dragBgColor;

    @Column(name = "borderColor")
    private String borderColor;
}
