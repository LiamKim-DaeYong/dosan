package com.samin.dosan.core.parameter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class SearchParam {

    private LocalDate startDate;

    private LocalDate endDate;

    private String selectKey;

    private String searchWorld;
}
