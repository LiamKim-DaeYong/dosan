package com.samin.dosan.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Getter @Setter
public class SearchParam {

    private LocalDate startDate;

    private LocalDate endDate;

    private String selectKey;

    private String searchWorld;
}
