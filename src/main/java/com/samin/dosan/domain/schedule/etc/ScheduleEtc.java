package com.samin.dosan.domain.schedule.etc;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long id;

    @Column(length = 255)
    private String categoryName;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String location;

    @Column
    private Boolean isAllDay;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;






}
