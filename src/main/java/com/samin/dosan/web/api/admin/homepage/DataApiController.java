package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.data.promotion.Promotion;
import com.samin.dosan.domain.homepage.data.promotion.PromotionService;
import com.samin.dosan.domain.homepage.data.webtoon.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataApiController {

    private final PromotionService promotionService;
    private final WebtoonService webtoonService;

    @PostMapping("/promotion/add")
    public ResponseEntity save(@Valid @RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.save(promotion.init()));
    }
}
