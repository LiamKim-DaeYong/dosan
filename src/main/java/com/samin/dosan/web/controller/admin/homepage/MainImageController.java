package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.mainImage.MainImage;
import com.samin.dosan.domain.homepage.mainImage.MainImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mainImage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MainImageController {

    private final MainImageService mainImageService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<MainImage> result = mainImageService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "homepage/mainImage/list";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute MainImage mainImage) {
        return "homepage/mainImage/addForm";
    }

//    @PostConstruct
//    public void init() {
//        for (int i = 1; i < 101; i++) {
//            MainImage mainImage = MainImage.builder()
//                    .fileId(1L)
//                    .postYn("Y")
//                    .postSeq(1)
//                    .title("제목"+i)
//                    .regDt(LocalDate.now())
//                    .build();
//
//            mainImageService.save(mainImage);
//        }
//    }

//    @PostMapping("/page")
//    public String mainImagePage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
//        Page<MainImageResponse> responseList = mainImageService.getMainImageList_admin(page, filterDto);
//
//        model.addAttribute("page", page);
//        model.addAttribute("filter", filterDto);
//        model.addAttribute("mainImageList", responseList);
//
//        return "homepage/mainImage/list :: mainImageFrag";
//    }
//

//
//    @GetMapping("/detail/{id}")
//    public String mainImageDetail(@PathVariable("id") Long id, Model model) {
//        MainImageResponse response = mainImageService.getMainImage_admin(id);
//
//        model.addAttribute("mainImage", response);
//
//        return "homepage/mainImage/detail";
//    }
//
//    @GetMapping("/modify/{id}")
//    public String mainImageModify(@PathVariable("id") Long id, Model model) {
//        MainImageResponse response = mainImageService.getMainImage_admin(id);
//
//        model.addAttribute("mainImage", response);
//
//        return "homepage/mainImage/modify";
//    }
}
