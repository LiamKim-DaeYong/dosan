package com.samin.dosan.domain.schedule.etc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleEtc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 100)
    private Long scheduleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ScheduleEtcType scheduleEtcType;

    @Column(length = 255)
    private String categoryName;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String location;

    @Column
    private Boolean isAllDay;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end;






}
