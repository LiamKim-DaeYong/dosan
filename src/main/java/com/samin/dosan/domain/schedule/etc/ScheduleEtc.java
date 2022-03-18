package com.samin.dosan.domain.schedule.etc;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleEtc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private Long id;

    @Column
    private String calendarId;

    @Column
    private String title;

    @Column
    private String location;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;






}
