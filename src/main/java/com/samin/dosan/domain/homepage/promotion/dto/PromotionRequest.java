package com.samin.dosan.domain.homepage.promotion.dto;

import com.samin.dosan.domain.homepage.promotion.Promotion;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PromotionRequest {

    private Long id;

    private String title;

    private String code;

    private String author;

    private LocalDate regDt;

    public Promotion toEntity() {
        return Promotion.builder()
                .id(this.id)
                .code(this.code)
                .title(this.title)
                .author(this.author)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .build();
    }
}
