package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.promotion.PromotionService;
import com.samin.dosan.domain.homepage.promotion.dto.PromotionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * NOTE : 관리자 - 홍보동영상 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/promotion")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public String promotion(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<PromotionResponse> promotionList = promotionService.getPromotionList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("promotionList", promotionList);

        return "homepage/data/promotion/list";
    }

    @PostMapping("/page")
    public String promotionPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<PromotionResponse> promotionList = promotionService.getPromotionList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("promotionList", promotionList);

        return "homepage/data/promotion/list :: promotionFrag";
    }

    @GetMapping("/regist")
    public String promotionRegist() {
        return "homepage/data/promotion/regist";
    }

    @GetMapping("/detail/{id}")
    public String promotionDetail(@PathVariable("id") Long id, Model model) {
        PromotionResponse response = promotionService.getPromotion_admin(id);

        model.addAttribute("promotion", response);

        return "homepage/data/promotion/detail";
    }

    @GetMapping("/modify/{id}")
    public String promotionModify(@PathVariable("id") Long id, Model model) {
        PromotionResponse response = promotionService.getPromotion_admin(id);

        model.addAttribute("promotion", response);

        return "homepage/data/promotion/modify";
    }
}
