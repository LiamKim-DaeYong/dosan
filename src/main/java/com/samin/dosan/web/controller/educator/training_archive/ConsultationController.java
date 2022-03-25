package com.samin.dosan.web.controller.educator.training_archive;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.consultation.Consultation;
import com.samin.dosan.domain.training_archive.consultation.ConsultationService;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.training_archive.ConsultationSave;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/consultation")
// 수련협의자료
public class ConsultationController {

    private final ConsultationService consultationArchiveService;
    private final UserService userService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Consultation> result = consultationArchiveService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "/educator/training_archive/consultation/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Consultation consultation) {
        return "/educator/training_archive/consultation/addView";
    }

    @GetMapping("/detail")
    public String detailView() {
        return "/educator/training_archive/consultation/detailView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/training_archive/consultation/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            Consultation consultation = Consultation.builder()
                    .user(userService.findById("educator"))
                    .title("제목"+i)
                    .content("내용"+i)
                    .used(Used.Y)
                    .regDt(LocalDate.now())
                    .build();

            consultationArchiveService.save(consultation);
        }
    }
}
