package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.code.homepage.PostType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.main_image.MainImage;
import com.samin.dosan.domain.homepage.main_image.MainImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/main-image")
public class MainImageController {

    private final MainImageService mainImageService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<MainImage> result = mainImageService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/homepage/main_image/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute MainImage mainImage) {
        return "admin/homepage/main_image/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("mainImage", mainImageService.findById(id));

        return "admin/homepage/main_image/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("mainImage", mainImageService.findById(id));

        return "admin/homepage/main_image/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            MainImage mainImage = MainImage.builder()
                    .title("제목"+i)
                    .postYn(PostType.N)
                    .sort(1)
                    .originFilename("파일")
                    .storeFileName("파일")
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            mainImageService.save(mainImage);
        }
    }
}
