package com.samin.dosan.web.dto.training.program.entry;

import com.querydsl.core.annotations.QueryProjection;
import com.samin.dosan.domain.training.program.entry.entity.InputType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class EntryProgramList {

    private Long id;

    private String clientNm;

    private LocalDate startDate;

    private LocalDate endDate;

    private InputType inputType;

    private Integer groups;

    private String writer;

    private LocalDateTime registDt;

    @QueryProjection
    public EntryProgramList(Long id, String clientNm, LocalDate startDate, LocalDate endDate, InputType inputType, Integer groups, String writer, LocalDateTime registDt) {
        this.id = id;
        this.clientNm = clientNm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inputType = inputType;
        this.groups = groups;
        this.writer = writer;
        this.registDt = registDt;
    }

    //==================   조회 메서드   ==================//
    public String getDays() {
        long days = ChronoUnit.DAYS.between(this.startDate, this.endDate);
        return days + "박" + (days + 1) + "일";
    }
}
