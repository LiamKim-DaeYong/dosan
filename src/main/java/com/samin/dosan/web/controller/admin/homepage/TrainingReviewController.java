package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_review.TrainingReview;
import com.samin.dosan.domain.homepage.training_review.TrainingReviewService;
import com.samin.dosan.domain.homepage.type.SecretType;
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
@RequestMapping("/admin/homepage/training-review")
public class TrainingReviewController {

    private final TrainingReviewService trainingReviewService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        Page<TrainingReview> result = trainingReviewService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/homepage/training_review/mainView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("trainingReview", trainingReviewService.findById(id));

        return "admin/homepage/training_review/detailView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 1000; i++) {
            TrainingReview review = TrainingReview.builder()
                    .hit(0)
                    .secretType(SecretType.N)
                    .password(null)
                    .author("작성자"+i)
                    .title("제목"+i)
                    .content("내영"+i)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            trainingReviewService.save(review);
        }
    }
}
