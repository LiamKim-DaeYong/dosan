package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.mainImage.MainImageService;
import com.samin.dosan.domain.homepage.mainImage.dto.MainImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * NOTE : 관리자 - 메인이미지 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/mainImage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MainImageController {

    private final MainImageService mainImageService;

    @GetMapping
    public String mainImage(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<MainImageResponse> responseList = mainImageService.getMainImageList_admin(page,filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("mainImageList", responseList);

        return "homepage/mainImage/list";
    }

    @PostMapping("/page")
    public String mainImagePage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<MainImageResponse> responseList = mainImageService.getMainImageList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("mainImageList", responseList);

        return "homepage/mainImage/list :: mainImageFrag";
    }

    @GetMapping("/regist")
    public String mainImageRegist() {
        return "homepage/mainImage/regist";
    }

    @GetMapping("/detail/{id}")
    public String mainImageDetail(@PathVariable("id") Long id, Model model) {
        MainImageResponse response = mainImageService.getMainImage_admin(id);

        model.addAttribute("mainImage", response);

        return "homepage/mainImage/detail";
    }

    @GetMapping("/modify/{id}")
    public String mainImageModify(@PathVariable("id") Long id, Model model) {
        MainImageResponse response = mainImageService.getMainImage_admin(id);

        model.addAttribute("mainImage", response);

        return "homepage/mainImage/modify";
    }
}
