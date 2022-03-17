package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.DataType;
import com.samin.dosan.domain.homepage.data.promotion.Promotion;
import com.samin.dosan.domain.homepage.data.promotion.PromotionService;
import com.samin.dosan.domain.homepage.data.webtoon.Webtoon;
import com.samin.dosan.domain.homepage.data.webtoon.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/data")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DataController {

    private final PromotionService promotionService;
    private final WebtoonService webtoonService;

    @GetMapping("/{type}")
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        DataType dataType = DataType.valueOf(type.toUpperCase());

        if (dataType.equals(DataType.PROMOTION)) {
            Page<Promotion> result = promotionService.findAll(searchParam, pageable);
            model.addAttribute("result", result);
        } else {
            Page<Webtoon> result = webtoonService.findAll(searchParam, pageable);
            model.addAttribute("result", result);
        }
        model.addAttribute("dataTypes", DataType.values());
        model.addAttribute("dataType", DataType.valueOf(type.toUpperCase()));

        return "homepage/data/data";
    }

    /* 홍보동영상 */
    @GetMapping("/promotion/add")
    public String promotionDetailForm(@ModelAttribute Promotion promotion, Model model) {

        model.addAttribute("dataTypes", DataType.values());
        model.addAttribute("dataType", DataType.PROMOTION);
        return "homepage/data/promotion/addForm";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 1000; i++) {
            Promotion promotion = Promotion.builder()
                    .title("제목"+i)
                    .code(String.valueOf(i))
                    .author("작성자"+i)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            Webtoon webtoon = Webtoon.builder()
                    .pdfId(1L)
                    .previewId(1L)
                    .author("작성자"+i)
                    .title("제목"+i)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            promotionService.save(promotion);
            webtoonService.save(webtoon);
        }
    }
}
