package com.samin.dosan.domain.homepage.promotion.dto;

import com.samin.dosan.domain.homepage.promotion.Promotion;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PromotionResponse {

    private Long id;

    private String title;

    private String code;

    private String author;

    private LocalDate regDt;

    public PromotionResponse(Promotion promotion) {
        this.id = promotion.getId();
        this.code = promotion.getCode();
        this.title = promotion.getTitle();
        this.author = promotion.getAuthor();
        this.regDt = promotion.getRegDt();
    }
}
