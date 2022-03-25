package com.samin.dosan.domain.schedule.etc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleEtc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_etc_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ScheduleEtcType scheduleEtcType;

    private String categoryNm;

    private String title;

    private String location;

    private Boolean isAllDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end;

    /*================== Business Logic ==================*/
    public static ScheduleEtc of(ScheduleEtc scheduleEtc,ScheduleEtcType scheduleEtcType) {
        ScheduleEtc saveScheduleEtc = new ScheduleEtc();
        if(scheduleEtc.id != null) {
            saveScheduleEtc.id = scheduleEtc.getId();
        }
        saveScheduleEtc.categoryNm = scheduleEtc.getCategoryNm();
        saveScheduleEtc.scheduleEtcType = scheduleEtcType;
        saveScheduleEtc.title = scheduleEtc.getTitle();
        saveScheduleEtc.location = scheduleEtc.getLocation();
        saveScheduleEtc.isAllDay = scheduleEtc.getIsAllDay();
        saveScheduleEtc.start = scheduleEtc.getStart();
        saveScheduleEtc.end = scheduleEtc.getEnd();
        return saveScheduleEtc;
    }

    @Builder(builderMethodName = "test")
    public ScheduleEtc(Long id, ScheduleEtcType scheduleEtcType,
                       String categoryNm, String title,
                       String location, Boolean isAllDay, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.scheduleEtcType = scheduleEtcType;
        this.categoryNm = categoryNm;
        this.title = title;
        this.location = location;
        this.isAllDay = isAllDay;
        this.start = start;
        this.end = end;
    }
}
